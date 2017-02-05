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

    public BaseImageActor(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
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

}















