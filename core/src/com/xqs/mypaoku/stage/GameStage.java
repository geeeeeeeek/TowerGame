package com.xqs.mypaoku.stage;

import com.badlogic.gdx.Gdx;
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
import com.xqs.mypaoku.actor.npc.Life;
import com.xqs.mypaoku.actor.npc.Pause;
import com.xqs.mypaoku.actor.npc.Popup;
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


/**
 * 主游戏舞台（主要的游戏逻辑都在这里）
 */
public class GameStage extends BaseStage {

    private static final String TAG = "GameStage";

    public World world;

    private Bg bgActor;

    private Player playerActor;

    private Tower tower;

    private List<Life> lifeList = new ArrayList<Life>();

    private Pause pause;

    private Popup popup;

    private List<BaseEnemy> enemyList = new ArrayList<BaseEnemy>();

    private List<BaseBullet> bulletList = new ArrayList<BaseBullet>();

    public static GameState gameState;

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

    public void init() {
        Gdx.app.log(TAG, "init==========");

        // clear before actors
        getRoot().clear();

        /*
         * 创建背景
         */
        bgActor = new Bg(this.getMainGame());
        addActor(bgActor);


        /**
         * 创建tower
         */
        tower = new Tower(this.getMainGame());
        addActor(tower);

        /** 暂停 **/
        pause = new Pause(this.getMainGame());
        addActor(pause);

        /** popup **/
        popup = new Popup(this.getMainGame());
        addActor(popup);

        pause.setTouchable(Touchable.enabled);
        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getMainGame().showLevelScreen();
            }
        });

		/*
         * 创建player
		 */
        playerActor = new Player(this.getMainGame());
        playerActor.setLife(3);
        addActor(playerActor);

        updateLifes();

        /** 子弹世界 **/
        world = Box2DManager.createWorld();


        // mock data : don't spell mistakes

        currentLevelIndex = 0;
        int currentLevel[][] = Level.levels[currentLevelIndex];
        for (int i = 0; i < currentLevel.length; i++) {
            int enemy[] = currentLevel[i];
            enemyOrderMap.put(enemy[0], enemy[1]);
        }


		/*
		 * 初始为游戏准备状态
		 */
        ready();
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
        gameState = GameState.ready;

        //清空子弹
        for (BaseBullet bullet : bulletList) {
            getRoot().removeActor(bullet);
        }
        bulletList.clear();

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


    /**
     * 生成敌人
     */
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
        }else if (type == EnemyType.WONIU) {
            WoniuEnemy enemy = new WoniuEnemy(getMainGame());
            addActor(enemy);
            enemyList.add(enemy);
        }

    }


    @Override
    public void act(float delta) {
        super.act(delta);


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


        Box2DManager.doPhysicsStep(world);

        drawBullets();

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
        if (enemyOrderMap.containsKey(counter)) {
            int type = enemyOrderMap.get(counter);
            generateEnemy(type);
        }

    }





    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        float ratio = Gdx.graphics.getWidth() / (getMainGame().getWorldWidth());
        if (gameState == GameState.ready) {
            float x = playerActor.getRightX();
            float y = playerActor.getY() + playerActor.getHeight() / 2;

            if (screenX < (Tower.getStopX() * ratio)) {
                screenX = (int) (Tower.getStopX() * ratio);
            }
            generatePlayerBullet(Bullet.PLAYER, screenX, screenY, x, y);
        }

        return super.touchUp(screenX,screenY,pointer,button);
    }
}



