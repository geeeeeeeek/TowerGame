package com.xqs.mypaoku.stage.base;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.util.Util;


/**
 * 舞台基类
 *
 */
public abstract class BaseStage extends Stage {

    private MyPaokuGame mainGame;

    /** 舞台是否可见（是否更新和绘制） */
    private boolean visible = true;

    /**计数相关**/
    private float timeCounter=0f;
    private float next=0f;
    private int counter=0;

    public BaseStage(MyPaokuGame mainGame, Viewport viewport) {
        super(viewport);
        this.mainGame = mainGame;
    }

    public MyPaokuGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        timeCounter+=delta;

        if((float)timeCounter>next){
            next+=0.1f;
            counter++;
            orderAct(delta,counter);
        }
    }


    //api

    public abstract void orderAct(float delta,int counter);
}
















