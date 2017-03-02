package com.xqs.mypaoku.stage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.Bg;
import com.xqs.mypaoku.actor.Bullet;
import com.xqs.mypaoku.actor.Enemy;
import com.xqs.mypaoku.actor.Player;
import com.xqs.mypaoku.actor.Road;
import com.xqs.mypaoku.actor.Tower;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.CollisionUtils;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * 主游戏舞台（主要的游戏逻辑都在这里）
 *
 */
public class GameStage extends BaseStage {

	private static final String TAG="GameStage";

    /** 背景 */
    private Bg bgActor;

	/** 主角 **/
	private Player playerActor;

	/** 塔 **/
	private Tower tower;

	/**路面**/
	private Road roadActor;

	/** 敌人容器 **/
	private List<Enemy> enemyList=new ArrayList<Enemy>();

	/**子弹容器**/
	private List<Bullet> bulletList=new ArrayList<Bullet>();

	/** 游戏状态 */
	public static GameState gameState;

	/** 敌人顺序 **/
	private HashMap<Integer,Integer> enemyOrderMap=new HashMap<Integer,Integer>();


    public GameStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init() {


        /*
         * 创建背景
         */
        bgActor = new Bg(this.getMainGame());
        addActor(bgActor);

		/**
		 * 创建tower
		 */
		tower=new Tower(this.getMainGame());
		addActor(tower);

		/*
		 * 创建player
		 */
		playerActor=new Player(this.getMainGame());
		addActor(playerActor);


		roadActor=new Road(this.getMainGame());
		addActor(roadActor);


		// mock data
		enemyOrderMap.put(1,1);
		enemyOrderMap.put(11,1);
		enemyOrderMap.put(12,1);
		enemyOrderMap.put(30,1);
		enemyOrderMap.put(50,1);

		/*
		 * 初始为游戏准备状态
		 */
		ready();
    }
    
    /**
	 * 游戏状态改变方法01: 游戏准备状态
	 */
	public void ready() {
		gameState = GameState.ready;

		//清空子弹
		for(Bullet bullet:bulletList){
			getRoot().removeActor(bullet);
		}
		bulletList.clear();

	}

	/**
	 * 生成子弹
	 */
	private void generateBullet(int bulletType, int screenX, int screenY){
		Bullet bullet=new Bullet(getMainGame(),bulletType,screenX,screenY);

		if(bulletType==1){
//			bullet.setPosition(playerActor.getX(),300);
		}
		addActor(bullet);
		bulletList.add(bullet);
	}

	/**
	 * 生成敌人
     */
	private void generateEnemy(int type){
		Enemy enemy=new Enemy(getMainGame());
		addActor(enemy);
		enemyList.add(enemy);
	}


	@Override
	public void act(float delta) {
		super.act(delta);

		//子弹与敌人碰撞检测
		for(Bullet bullet:bulletList){
			for(Enemy enemy:enemyList){
				if(CollisionUtils.isCollision(bullet,enemy,10)){
					Util.log("collision:","yes");

					getRoot().removeActor(bullet);
//					getRoot().removeActor(enemy);

					bullet.setState(Bullet.DEAD);
					enemy.setState(Enemy.HURT);
				}
			}
		}

		//移除死亡的子弹
	    Iterator<Bullet> bulletIterator= bulletList.iterator();
		while (bulletIterator.hasNext()){
			Bullet bullet=bulletIterator.next();
			if(bullet.getState()==Bullet.DEAD){
				bulletIterator.remove();
			}
		}

		//移除死亡的敌人
		Iterator<Enemy> enemyIterator= enemyList.iterator();
		while (enemyIterator.hasNext()){
			Enemy enemy=enemyIterator.next();
			if(enemy.getState()==Enemy.DEAD){
				enemyIterator.remove();
			}
		}
	}

	@Override
	public void orderAct(float delta, int counter) {
		Util.log(TAG,"计时器="+counter);
		if(enemyOrderMap.containsKey(counter)){
			int type=enemyOrderMap.get(counter);
			generateEnemy(type);
		}

	}



	@Override
	public void dispose() {
		super.dispose();
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (gameState == GameState.ready) {
			Util.log("touch:",screenX+" "+screenY+ " "+pointer+" "+button);
			generateBullet(1,screenX,screenY);
		}


		return true;
	}
}


















