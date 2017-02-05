package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;

/**
 * 动画演员基类
 *
 */
public abstract class BaseAnimationActor extends AnimationActor {

    private MyPaokuGame mainGame;

    public BaseAnimationActor(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
    }
    
    public BaseAnimationActor(MyPaokuGame mainGame, Animation animation) {
        super(animation);
        this.mainGame = mainGame;
    }
    
    public BaseAnimationActor(MyPaokuGame mainGame, float frameDuration, Array<? extends TextureRegion> keyFrames) {
		super(frameDuration, keyFrames);
		this.mainGame = mainGame;
    }

    public BaseAnimationActor(MyPaokuGame mainGame, float frameDuration, TextureRegion... keyFrames) {
        super(frameDuration, keyFrames);
        this.mainGame = mainGame;
    }

    public MyPaokuGame getMainGame() {
        return mainGame;
    }

    public void setMainGame(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
    }

}















