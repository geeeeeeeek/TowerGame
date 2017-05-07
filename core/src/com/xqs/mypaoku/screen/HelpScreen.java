package com.xqs.mypaoku.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.stage.HelpStage;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class HelpScreen extends ScreenAdapter {
    private MyPaokuGame game;
    private HelpStage helpStage;

    public HelpScreen(MyPaokuGame game){
        this.game = game;
        helpStage = new HelpStage(game, new ScalingViewport(Scaling.fit,
                game.getWorldWidth(),
                game.getWorldHeight()
        ));
    }

    public void init(){
        Gdx.input.setInputProcessor(helpStage);
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        if(null!=helpStage){
            helpStage.draw();
            helpStage.act();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (helpStage != null) {
            helpStage.dispose();
            helpStage = null;
        }
    }
}
