package com.xqs.mypaoku.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.stage.MenuStage;
import com.xqs.mypaoku.stage.SplashStage;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class MenuScreen extends ScreenAdapter {
    private MyPaokuGame mainGame;
    private MenuStage menuStage;

    public MenuScreen(MyPaokuGame mainGame) {
        this.mainGame = mainGame;
        menuStage = new MenuStage(mainGame, new ScalingViewport(Scaling.fit,
                mainGame.getWorldWidth(),
                mainGame.getWorldHeight()
        ));

    }

    public void init() {
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (null != menuStage) {
            menuStage.draw();
            menuStage.act();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (menuStage != null) {
            menuStage.dispose();
            menuStage = null;
        }
    }
}
