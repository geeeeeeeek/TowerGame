package com.xqs.mypaoku.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.Bg;
import com.xqs.mypaoku.actor.Bullet;
import com.xqs.mypaoku.actor.Enemy;
import com.xqs.mypaoku.actor.Player;
import com.xqs.mypaoku.actor.Road;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.CollisionUtils;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 主游戏舞台（主要的游戏逻辑都在这里）
 *
 */
public class GameStage extends BaseStage {

    /** 背景 */
    private Bg bgActor;

	/** 主角 **/
	private Player playerActor;

	/**路面**/
	private Road roadActor;

	/** 敌人容器 **/
	private List<Enemy> enemyList=new ArrayList<Enemy>();

	/**子弹容器**/
	private List<Bullet> bulletList=new ArrayList<Bullet>();

	/** 游戏状态 */
	public static GameState gameState;

	private float timeCounter;

	private int next=1;

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
		/*
		 * 创建player
		 */
		playerActor=new Player(this.getMainGame());
		addActor(playerActor);


		roadActor=new Road(this.getMainGame());
		addActor(roadActor);



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

		timeCounter=0.0f;
	}

	/**
	 * 生成子弹
	 */
	private void generateBullet(int bulletType){
		Bullet bullet=new Bullet(getMainGame(),bulletType);

		if(bulletType==1){
			bullet.setPosition(playerActor.getX(),playerActor.getY());
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
		timeCounter+=delta;
		if((int)timeCounter>next){
			Util.log("计时器timeCounter",(int)timeCounter+"");
			next++;


//			generateBullet(1);
			generateEnemy(1);
		}

		//子弹与敌人碰撞检测
		for(Bullet bullet:bulletList){
			for(Enemy enemy:enemyList){
				if(CollisionUtils.isCollision(bullet,enemy,10)){
					Util.log("collision:","yes");

					getRoot().removeActor(bullet);
					getRoot().removeActor(enemy);

					bullet.setState(Bullet.DEAD);
					enemy.setState(Enemy.DEAD);
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
			if(enemy.getState()==Bullet.DEAD){
				enemyIterator.remove();
			}
		}
	}



	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (gameState == GameState.ready) {
			generateBullet(1);
		}


		return true;
	}


}


















