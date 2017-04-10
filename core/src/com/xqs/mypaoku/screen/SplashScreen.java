package com.xqs.mypaoku.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.app.Prefs;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.stage.SplashStage;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class SplashScreen extends ScreenAdapter {

    private MyPaokuGame mainGame;
    private SplashStage splashStage;


    public SplashScreen(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
        splashStage = new SplashStage(mainGame, new ScalingViewport(Scaling.fit,
                mainGame.getWorldWidth(),
                mainGame.getWorldHeight()
        ));


        // 将输入处理设置到
        Gdx.input.setInputProcessor(splashStage);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(null!=splashStage){
            splashStage.draw();
            splashStage.act();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (splashStage != null) {
            splashStage.dispose();
            splashStage = null;
        }
    }
}
