package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.ImageActor;


/**
 * 演员基类
 *
 */
public abstract class BaseImageActor extends ImageActor {

    private MyPaokuGame mainGame;
    private TimerListener listener;

    /**
     * 计数相关
     **/
    private float timeCounter = 0f;
    private float next = 0f;
    private int counter = 0;

    public BaseImageActor(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
    }

    public void setTimerListener(TimerListener listener){
        this.listener = listener;
    }


    public BaseImageActor(MyPaokuGame mainGame, TextureRegion region) {
        super(region);
        this.mainGame = mainGame;
    }

    public MyPaokuGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(null!=listener){
            timeCounter += delta;
            if (timeCounter > next) {
                next += 0.1f;
                counter++;
                listener.orderAct(delta, counter);
            }
        }

    }


    public interface TimerListener{
        void orderAct(float delta, int counter);
    }
}















