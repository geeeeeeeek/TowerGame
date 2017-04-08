package com.xqs.mypaoku.stage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.SplashActor;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class SplashStage extends BaseStage {
    public static final String TAG = "SplashStage";

    public SplashStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);

        SplashActor splashActor = new SplashActor(mainGame);
        addActor(splashActor);
    }

    @Override
    public void orderAct(float delta, int counter) {

        if(counter>20){
            getMainGame().showMenuScreen();
        }
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
