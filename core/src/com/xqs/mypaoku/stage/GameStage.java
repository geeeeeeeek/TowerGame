package com.xqs.mypaoku.stage;

import com.badlogic.gdx.Gdx;
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
import com.xqs.mypaoku.actor.bullet.LajiaoBullet;
import com.xqs.mypaoku.actor.bullet.PlaneBullet;
import com.xqs.mypaoku.actor.bullet.PlayerOneBullet;
import com.xqs.mypaoku.actor.bullet.PlayerTwoBullet;
import com.xqs.mypaoku.actor.bullet.QiukuiBullet;
import com.xqs.mypaoku.actor.bullet.YangcongBullet;
import com.xqs.mypaoku.actor.enemy.CaihuaEnemy;
import com.xqs.mypaoku.actor.enemy.DacongEnemy;
import com.xqs.mypaoku.actor.enemy.HuangguaEnemy;
import com.xqs.mypaoku.actor.enemy.LajiaoEnemy;
import com.xqs.mypaoku.actor.enemy.LuoboEnemy;
import com.xqs.mypaoku.actor.enemy.MushuEnemy;
import com.xqs.mypaoku.actor.enemy.QieziEnemy;
import com.xqs.mypaoku.actor.enemy.QiukuiEnemy;
import com.xqs.mypaoku.actor.enemy.WoniuEnemy;
import com.xqs.mypaoku.actor.enemy.YangcongEnemy;
import com.xqs.mypaoku.actor.enemy.YutouEnemy;
import com.xqs.mypaoku.actor.npc.BulletOneBg;
import com.xqs.mypaoku.actor.npc.BulletTwoBg;
import com.xqs.mypaoku.actor.npc.Fade;
import com.xqs.mypaoku.actor.npc.Finger;
import com.xqs.mypaoku.actor.npc.Life;
import com.xqs.mypaoku.actor.npc.Menu;
import com.xqs.mypaoku.actor.npc.Next;
import com.xqs.mypaoku.actor.npc.NoBulletPopup;
import com.xqs.mypaoku.actor.npc.Pause;
import com.xqs.mypaoku.actor.npc.Play;
import com.xqs.mypaoku.actor.npc.Popup;
import com.xqs.mypaoku.actor.npc.Replay;
import com.xqs.mypaoku.actor.npc.Score;
import com.xqs.mypaoku.app.Prefs;
import com.xqs.mypaoku.res.EnemyType;
import com.xqs.mypaoku.res.Level;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.Box2DManager;
import com.xqs.mypaoku.util.CollisionUtils;
import com.xqs.mypaoku.util.GameState;

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

    private BulletOneBg bulletOneBg;
    private BulletTwoBg bulletTwoBg;

    private Play play;

    private Replay replay;

    private Next next;

    private Menu menu;

    private NoBulletPopup noBulletPopup ;

    private Finger finger;

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

        // init bullet number
        Prefs.getPrefs().setPlayerBulletOneLeftNumber(30);
        Prefs.getPrefs().setPlayerBulletTwoLeftNumber(30);

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
        bulletOneBg = new BulletOneBg(getMainGame());
        addActor(bulletOneBg);
        bulletTwoBg = new BulletTwoBg(getMainGame());
        addActor(bulletTwoBg);

//        bulletOneBg.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                Util.log(TAG,"clicked");
//            }
//        });

		// player
        playerActor = new Player(this.getMainGame());
        playerActor.setLife(5);
        addActor(playerActor);

        updateLifes();

        // nobulletpopup
        noBulletPopup = new NoBulletPopup(getMainGame());
        addActor(noBulletPopup);


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
        SoundHelper.playGameOver();
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

        int LifeLeft = 20;
        for (Life life : lifeList) {
            life.setPosition(LifeLeft, getMainGame().getWorldHeight() - 50);
            addActor(life);
            LifeLeft += 50;
        }
    }

    // gamestage ready
    public void ready() {
        gameState = GameState.GAMING;

        // remove bullet actors
        for (BaseBullet bullet : bulletList) {
            getRoot().removeActor(bullet);
        }

        // clear enemylist
        enemyList.clear();

        // clear bulletlist
        bulletList.clear();

        // stop all sounds
        SoundHelper.stopAllSound();

        // play bg music
        SoundHelper.playBgMusic();

    }

    public static int getGameState() {
        return gameState;
    }

    public static void setGameState(int state) {
        gameState = state;
    }

    // gener player bullet
    public void generatePlayerBullet(int bulletType, int clickX, int clickY, float positionX, float positionY) {
        if(bulletType == BaseBullet.PLAYER_ONE) {
            PlayerOneBullet bullet = new PlayerOneBullet(getMainGame(), clickX, clickY, positionX, positionY, world);
            addActor(bullet);
            bulletList.add(bullet);
            playerActor.shoot();
        }else if(bulletType == BaseBullet.PLAYER_TWO){
            PlayerTwoBullet bullet = new PlayerTwoBullet(getMainGame(), clickX, clickY, positionX, positionY, world);
            addActor(bullet);
            bulletList.add(bullet);
            playerActor.shoot();
        }
    }

    // gener eneny bullet
    public void generateEnemyBullet(int bulletType, float positionX, float positionY) {

        if (bulletType == BaseBullet.CAIHUA) {
            CaihuaBullet bullet = new CaihuaBullet(getMainGame(), positionX, positionY, world);
            addActor(bullet);
            bulletList.add(bullet);
        } else if (bulletType == BaseBullet.PLANE) {
            PlaneBullet bullet = new PlaneBullet(getMainGame(), positionX, positionY, world);
            addActor(bullet);
            bulletList.add(bullet);
        } else if (bulletType == BaseBullet.YANGCONG){
            YangcongBullet bullet = new YangcongBullet(getMainGame(),positionX,positionY,world);
            addActor(bullet);
            bulletList.add(bullet);
        } else if(bulletType == BaseBullet.QIUKUI){
            QiukuiBullet bullet = new QiukuiBullet(getMainGame(),positionX,positionY,world);
            addActor(bullet);
            bulletList.add(bullet);
        } else if(bulletType == BaseBullet.LAJIAO){
            LajiaoBullet bullet = new LajiaoBullet(getMainGame(),positionX,positionY,world);
            addActor(bullet);
            bulletList.add(bullet);

        }
    }


    // generateEnemy
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
        } else if(type == EnemyType.YANGCONG){
            YangcongEnemy enemy = new YangcongEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if(type == EnemyType.LUOBO){
            LuoboEnemy enemy = new LuoboEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if(type == EnemyType.QIEZI){
            QieziEnemy enemy = new QieziEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        } else if(type == EnemyType.QIUKUI){
            QiukuiEnemy enemy = new QiukuiEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        }
    }

    // 弹窗
    public void showPopup() {

        hidePopup();

        SoundHelper.pauseBgMusic();

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
        SoundHelper.playBgMusic();
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

    public void minusLife(){
        playerActor.minusLife();
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
                        case BaseBullet.PLAYER_ONE:
                            //此子弹会自爆
                            if (bullet.getState() == BaseBullet.EXPLODE) {
                                enemy.hurt();
                                SoundHelper.playGetCoinSound();
                                int random = MathUtils.random(1,10);
                                Score.score=String.valueOf(Integer.parseInt(Score.score)+random);
                            }
                            break;
                        case BaseBullet.PLAYER_TWO:
                            //此子弹会自爆
                            if (bullet.getState() == BaseBullet.EXPLODE) {
                                enemy.hurt();
                                SoundHelper.playGetCoinSound();
                                int random = MathUtils.random(1,10);
                                Score.score=String.valueOf(Integer.parseInt(Score.score)+random);
                            }
                            break;
                    }

                }
            }


            //与塔
            if (CollisionUtils.isCollision(bullet, tower, 10) && bullet.getState() == BaseBullet.FLY) {
                if(bullet.getBulletType() == BaseBullet.CAIHUA
                        || bullet.getBulletType() == BaseBullet.PLANE
                        || bullet.getBulletType() == BaseBullet.YANGCONG
                        || bullet.getBulletType() == BaseBullet.QIUKUI
                        || bullet.getBulletType() == BaseBullet.LAJIAO){

                    playerActor.minusLife();
                    bullet.explode();
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

//        if (enemyOrderMap.containsKey(counter)) {
//            int order = enemyOrderMap.get(counter);
//            if(order == EnemyType.END){
//                levelPoint = true;
//            }else if(counter%10==0) {
//                int type = MathUtils.random(20,30);
//                generateEnemy(type);
//            }
//        }

        // 结束或boss
        if(enemyOrderMap.containsKey(counter)) {
            int order = enemyOrderMap.get(counter);
            if(order == EnemyType.BOSS){
                int type = MathUtils.random(28,30);
                generateEnemy(type);
            }else if(order == EnemyType.END){
                levelPoint = true;
            }
        }
        // 普通
        else if(!levelPoint){
            if(currentLevelIndex<3 && counter % 20 == 0){
                int type = MathUtils.random(20,27);
                generateEnemy(type);
            } else if(currentLevelIndex>=3 && counter % 10 == 0){
                int type = MathUtils.random(20,27);
                generateEnemy(type);
            }
        }


        if(levelPoint){
            if(enemyList.size()<=0){
                Prefs.getPrefs().setPassedLevel(currentLevelIndex+1);
                setGameState(GameState.PASS);
                SoundHelper.playPass();
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

            int clickX = screenX;
            int clickY = screenY;

            int stopx = (int) (Tower.getStopX()*ratio);

//            if (screenX < stopx) {
//                clickX = stopx;
//            }


            if(clickY>Gdx.graphics.getHeight()/4 && screenX > (stopx-20)) {
                if(BulletOneBg.mode==1){
                    int leftBulletNumber = Prefs.getPrefs().getPlayerBulletOneLeftNumber();
                    if(leftBulletNumber>0){
                        generatePlayerBullet(BaseBullet.PLAYER_ONE, clickX, clickY, x, y);
                        Prefs.getPrefs().setPlayerBulletOneLeftNumber(leftBulletNumber-1);
                    }else {
                        noBulletPopup.setNoBulletPopupVisible();
                    }
                }else if(BulletTwoBg.mode==1){
                    int leftBulletNumber = Prefs.getPrefs().getPlayerBulletTwoLeftNumber();
                    if(leftBulletNumber>0) {
                        generatePlayerBullet(BaseBullet.PLAYER_TWO, clickX, clickY, x, y);
                        Prefs.getPrefs().setPlayerBulletTwoLeftNumber(leftBulletNumber-1);
                    }else{
                        noBulletPopup.setNoBulletPopupVisible();
                    }
                }
            }
        }


        return super.touchUp(screenX, screenY, pointer, button);
    }
}



