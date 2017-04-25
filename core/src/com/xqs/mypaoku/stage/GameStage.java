package com.xqs.mypaoku.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.Bg;
import com.xqs.mypaoku.actor.Bullet;
import com.xqs.mypaoku.actor.Player;
import com.xqs.mypaoku.actor.SoundHelper;
import com.xqs.mypaoku.actor.Tower;
import com.xqs.mypaoku.actor.base.BaseBullet;
import com.xqs.mypaoku.actor.base.BaseEnemy;
import com.xqs.mypaoku.actor.bullet.CaihuaBullet;
import com.xqs.mypaoku.actor.bullet.PlaneBullet;
import com.xqs.mypaoku.actor.bullet.PlayerBullet;
import com.xqs.mypaoku.actor.enemy.CaihuaEnemy;
import com.xqs.mypaoku.actor.enemy.DacongEnemy;
import com.xqs.mypaoku.actor.enemy.HuangguaEnemy;
import com.xqs.mypaoku.actor.enemy.LajiaoEnemy;
import com.xqs.mypaoku.actor.enemy.MushuEnemy;
import com.xqs.mypaoku.actor.enemy.WoniuEnemy;
import com.xqs.mypaoku.actor.enemy.YutouEnemy;
import com.xqs.mypaoku.actor.npc.BulletBg;
import com.xqs.mypaoku.actor.npc.Fade;
import com.xqs.mypaoku.actor.npc.Life;
import com.xqs.mypaoku.actor.npc.Menu;
import com.xqs.mypaoku.actor.npc.Next;
import com.xqs.mypaoku.actor.npc.Pause;
import com.xqs.mypaoku.actor.npc.Play;
import com.xqs.mypaoku.actor.npc.Popup;
import com.xqs.mypaoku.actor.npc.Replay;
import com.xqs.mypaoku.actor.npc.Score;
import com.xqs.mypaoku.app.Prefs;
import com.xqs.mypaoku.res.EnemyType;
import com.xqs.mypaoku.res.Level;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.Box2DManager;
import com.xqs.mypaoku.util.CollisionUtils;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class GameStage extends BaseStage {


    private static final String TAG = "GameStage";

    public World world;

    private Bg bgActor;

    private Player playerActor;

    private Tower tower;

    private List<Life> lifeList = new ArrayList<Life>();

    private Pause pause;

    private Fade fade;

    private Popup popup;

    private Score score;

    private BulletBg bulletBg;

    private Play play;

    private Replay replay;

    private Next next;

    private Menu menu;

    // sound
    private Sound scoreSound;
    private Music bgMusic;

    // level point
    private boolean levelPoint;

    private List<BaseEnemy> enemyList = new ArrayList<BaseEnemy>();

    private List<BaseBullet> bulletList = new ArrayList<BaseBullet>();

    private HashMap<Integer, Integer> enemyOrderMap = new HashMap<Integer, Integer>();

    private static GameStage instance;

    private int currentLevelIndex;

    private GameStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
    }

    public static synchronized GameStage getInstance(MyPaokuGame game) {
        if (instance == null) {
            instance = new GameStage(
                    game,
                    new ScalingViewport(Scaling.fit,
                            game.getWorldWidth(),
                            game.getWorldHeight()
                    )
            );
        }
        return instance;
    }

    public void init(int currentLevelIndex) {
        Gdx.app.log(TAG, "init level-->"+currentLevelIndex);

        this.currentLevelIndex = currentLevelIndex;

        // clear before actors
        getRoot().clear();

        // clear counter
        clearCounter();

        // bg
        bgActor = new Bg(this.getMainGame());
        addActor(bgActor);

        // tower
        tower = new Tower(this.getMainGame());
        addActor(tower);

        // pause
        pause = new Pause(this.getMainGame());
        addActor(pause);

        pause.setTouchable(Touchable.enabled);
        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(gameState == GameState.GAMING) {
                    gameState = GameState.PAUSE;
                    showPopup();
                }
            }
        });

        // score
        score = new Score(getMainGame());
        addActor(score);

        // BulletBg
        bulletBg = new BulletBg(getMainGame());
        bulletBg.setMode(BulletBg.MODE_ONE);
        addActor(bulletBg);
        bulletBg = new BulletBg(getMainGame());
        bulletBg.setMode(BulletBg.MODE_TWO);
        addActor(bulletBg);

		// player
        playerActor = new Player(this.getMainGame());
        playerActor.setLife(3);
        addActor(playerActor);

        updateLifes();

        /** 子弹世界 **/
        world = Box2DManager.createWorld();


        // current level data
        enemyOrderMap.clear();
        int currentLevel[][] = Level.levels[currentLevelIndex];
        for (int i = 0; i < currentLevel.length; i++) {
            int enemy[] = currentLevel[i];
            enemyOrderMap.put(enemy[0], enemy[1]);
        }

        // if reach end
        levelPoint = false;

        // sounds
        scoreSound = SoundHelper.getSound(getMainGame(),Res.Audios.AUDIO_SCORE);
        bgMusic = SoundHelper.getMusic(getMainGame(),Res.Audios.AUDIO_BG);

        bgMusic.setLooping(true);
        bgMusic.setVolume(0.6f);
        bgMusic.play();

		// ready
        ready();
    }

    public void replay(){
        init(currentLevelIndex);
    }

    public void next(){
        ++currentLevelIndex;
        // 已通全关
        if(currentLevelIndex>=Level.levels.length){
            currentLevelIndex = 0;
        }
        init(currentLevelIndex);
    }

    public void gameOver(){
        setGameState(GameState.GAMEOVER);
        showPopup();
    }

    // update lifes
    public void updateLifes() {

        // remove old list
        Iterator<Life> lifeIterator = lifeList.iterator();
        while (lifeIterator.hasNext()) {
            Life life = lifeIterator.next();
            life.remove();
        }
        lifeList.clear();

        // add new list
        for (int i = 0; i < playerActor.getLife(); i++) {
            lifeList.add(new Life(getMainGame()));
        }

        int LifeLeft = 0;
        for (Life life : lifeList) {
            life.setPosition(LifeLeft, getMainGame().getWorldHeight() - 50);
            addActor(life);
            LifeLeft += 50;
        }
    }

    /**
     * 游戏状态改变方法01: 游戏准备状态
     */
    public void ready() {
        gameState = GameState.GAMING;

        //清空子弹
        for (BaseBullet bullet : bulletList) {
            getRoot().removeActor(bullet);
        }

        // 清空敌人容器
        enemyList.clear();

        // 清空子弹容器
        bulletList.clear();

    }

    public static int getGameState() {
        return gameState;
    }

    public static void setGameState(int state) {
        gameState = state;
    }

    /**
     * 生成player子弹
     */
    public void generatePlayerBullet(int bulletType, int screenX, int screenY, float positionX, float positionY) {
        PlayerBullet bullet = new PlayerBullet(getMainGame(), screenX, screenY, positionX, positionY, world);
        addActor(bullet);
        bulletList.add(bullet);
        playerActor.shoot();
    }

    /**
     * 生成敌人子弹
     */
    public void generateEnemyBullet(int bulletType, float positionX, float positionY) {

        if (bulletType == BaseBullet.CAIHUA) {
            CaihuaBullet bullet = new CaihuaBullet(getMainGame(), positionX, positionY, world);
            addActor(bullet);
            bulletList.add(bullet);
        } else if (bulletType == BaseBullet.PLANE) {
            PlaneBullet bullet = new PlaneBullet(getMainGame(), positionX, positionY, world);
            addActor(bullet);
            bulletList.add(bullet);
        }
    }


    // 生成敌人
    private void generateEnemy(int type) {
        if (type == EnemyType.DACONG) {
            DacongEnemy enemy = new DacongEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if (type == EnemyType.CAIHUA) {
            CaihuaEnemy enemy = new CaihuaEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if (type == EnemyType.MUSHU) {
            MushuEnemy enemy = new MushuEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if (type == EnemyType.YUTOU) {
            YutouEnemy enemy = new YutouEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if (type == EnemyType.HUANGGUA) {
            HuangguaEnemy enemy = new HuangguaEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if (type == EnemyType.LAJIAO) {
            LajiaoEnemy enemy = new LajiaoEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if (type == EnemyType.WONIU) {
            WoniuEnemy enemy = new WoniuEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        }
    }

    // 弹窗
    public void showPopup() {

        hidePopup();

        bgMusic.pause();

        /** fade **/
        fade = new Fade(this.getMainGame());
        addActor(fade);

        /** popup **/
        popup = new Popup(this.getMainGame());
        addActor(popup);

        if (gameState == GameState.PAUSE) {
            play = new Play(this.getMainGame());
            addActor(play);
        } else if (gameState == GameState.GAMEOVER) {
            replay = new Replay(this.getMainGame());
            addActor(replay);
        } else if(gameState == GameState.PASS){
            next = new Next(this.getMainGame());
            addActor(next);
        }

        /** menu **/
        menu = new Menu(this.getMainGame());
        addActor(menu);
    }

    public void hidePopup() {
        bgMusic.play();
        if (fade != null) {
            fade.remove();
            fade = null;
        }
        if (popup != null) {
            popup.remove();
            popup = null;
        }
        if (play != null) {
            play.remove();
            play = null;
        }
        if (replay != null) {
            replay.remove();
            replay = null;
        }
        if(next != null){
            next.remove();
            next = null;
        }
        if (menu != null) {
            menu.remove();
            menu = null;
        }
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        if(getGameState()!=GameState.GAMING)
            return;

        // player life
        if(playerActor.getLife()<=0) {
            gameOver();
        }

        //子弹与其他碰撞检测
        for (BaseBullet bullet : bulletList) {
            //与敌人
            for (BaseEnemy enemy : enemyList) {
                if (CollisionUtils.isCollision(bullet, enemy, 12) && enemy.getState() == BaseEnemy.WALK) {
                    switch (bullet.getBulletType()) {
                        case BaseBullet.PLAYER:
                            //此子弹会自爆
                            if (bullet.getState() == BaseBullet.EXPLODE) {
                                enemy.hurt();
                                scoreSound.play();
                                int random = MathUtils.random(1,100);
                                Score.score=String.valueOf(Integer.parseInt(Score.score)+random);
                            }
                            break;
                    }

                }
            }


            //与塔
            if (CollisionUtils.isCollision(bullet, tower, 10) && bullet.getState() == BaseBullet.FLY) {
                switch (bullet.getBulletType()) {
                    case BaseBullet.CAIHUA:
                        playerActor.minusLife();
                        bullet.explode();
                        break;
                    case BaseBullet.PLANE:
                        playerActor.minusLife();
                        bullet.explode();
                        break;
                }

            }
        }


        //移除死亡的子弹
        Iterator<BaseBullet> bulletIterator = bulletList.iterator();
        while (bulletIterator.hasNext()) {
            BaseBullet bullet = bulletIterator.next();
            if (bullet.getState() == Bullet.DEAD) {
                getRoot().removeActor(bullet);
                bulletIterator.remove();
            }
        }

        //移除死亡的敌人
        Iterator<BaseEnemy> enemyIterator = enemyList.iterator();
        while (enemyIterator.hasNext()) {
            BaseEnemy enemy = enemyIterator.next();
            if (enemy.getState() == DacongEnemy.DEAD) {
                enemyIterator.remove();
            }
        }
    }


    @Override
    public void draw() {
        super.draw();

        if (gameState == GameState.GAMING) {
            Box2DManager.doPhysicsStep(world);
        }

        // draw world
        drawBullets();

        // remove world
        Box2DManager.removeBodies(world);


    }

    private synchronized void drawBullets() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {

            BaseBullet e = (BaseBullet) body.getUserData();

            if (e != null && e.getState() == BaseBullet.FLY) {
                e.setPosition(body.getPosition().x, body.getPosition().y);
//				e.setRotation(MathUtils.radiansToDegrees * body.getAngle());
            } else if (e.getState() == Bullet.EXPLODE) {
//				e.setRotation(0);
            }
        }
    }

    @Override
    public void orderAct(float delta, int counter) {
//		Util.log(TAG,"计时器="+counter);
        Score.score=String.valueOf(Integer.parseInt(Score.score)+1);
        if (enemyOrderMap.containsKey(counter)) {
            int type = enemyOrderMap.get(counter);
            if(type == EnemyType.END){
                levelPoint = true;
            }else {
                generateEnemy(type);
            }
        }

        Util.log(TAG,"count="+enemyList.size());
        if(levelPoint){
            if(enemyList.size()<=0){
                Prefs.getPrefs().setPassedLevel(currentLevelIndex+1);
                setGameState(GameState.PASS);
                showPopup();
            }
        }

    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        float ratio = Gdx.graphics.getWidth() / (getMainGame().getWorldWidth());
        if (gameState == GameState.GAMING) {
            float x = playerActor.getRightX();
            float y = playerActor.getY() + playerActor.getHeight() / 2;

            if (screenX < (Tower.getStopX() * ratio)) {
                screenX = (int) (Tower.getStopX() * ratio);
            }
            Util.log(TAG,"Click --> "+screenX+" "+screenY);
            if(screenY>Gdx.graphics.getHeight()/4) {
                generatePlayerBullet(Bullet.PLAYER, screenX, screenY, x, y);
            }
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }
}



