package com.xqs.mypaoku.stage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.SplashActor;
import com.xqs.mypaoku.app.Prefs;
import com.xqs.mypaoku.res.Constant;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class SplashStage extends BaseStage {
    public static final String TAG = "SplashStage";

    public static final int WAIT_SECONDS = 25;

    public SplashStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);

        SplashActor splashActor = new SplashActor(mainGame);
        addActor(splashActor);

//        Prefs.getPrefs().clear(); // test code
    }

    @Override
    public void orderAct(float delta, int counter) {

        if(counter>WAIT_SECONDS){
            getMainGame().showMenuScreen(Constant.SCREEN_SPLASH);
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
