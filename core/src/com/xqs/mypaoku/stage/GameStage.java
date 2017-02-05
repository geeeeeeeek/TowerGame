package com.xqs.mypaoku.stage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.BgActor;
import com.xqs.mypaoku.actor.EnemyActor;
import com.xqs.mypaoku.actor.PlayerActor;
import com.xqs.mypaoku.actor.RoadActor;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.GameState;


/**
 * 主游戏舞台（主要的游戏逻辑都在这里）
 *
 */
public class GameStage extends BaseStage {

    /** 背景 */
    private BgActor bgActor;

	/** 主角 **/
	private PlayerActor playerActor;

	/** 敌人 **/
	private EnemyActor enemyActor;

	/**路面**/
	private RoadActor roadActor;

	/** 游戏状态 */
	public static GameState gameState;

    public GameStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);
        init();
    }

    private void init() {
        /*
         * 创建背景
         */
        bgActor = new BgActor(this.getMainGame());
        addActor(bgActor);
		/*
		 * 创建player
		 */
		playerActor=new PlayerActor(this.getMainGame());
		addActor(playerActor);
		/**
		 * 创建敌人
		 */
		enemyActor=new EnemyActor(this.getMainGame());
		addActor(enemyActor);

		roadActor=new RoadActor(this.getMainGame());
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

	}


	@Override
	public void act(float delta) {
		super.act(delta);

	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (gameState == GameState.ready) {

		}


		return true;
	}

}


















