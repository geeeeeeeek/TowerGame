package com.xqs.mypaoku.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.stage.LevelStage;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class LevelScreen extends ScreenAdapter {

    private MyPaokuGame game;
    private LevelStage levelStage;

    public LevelScreen(MyPaokuGame game){
        this.game = game;
        levelStage = new LevelStage(game, new ScalingViewport(Scaling.fit,
                game.getWorldWidth(),
                game.getWorldHeight()
        ));
    }

    public void init(){
        Gdx.input.setInputProcessor(levelStage);
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        if(null!=levelStage){
            levelStage.draw();
            levelStage.act();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (levelStage != null) {
            levelStage.dispose();
            levelStage = null;
        }
    }
}
