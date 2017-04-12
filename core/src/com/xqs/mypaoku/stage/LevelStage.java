package com.xqs.mypaoku.stage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.level.LevelBg;
import com.xqs.mypaoku.stage.base.BaseStage;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class LevelStage extends BaseStage {
    public static final String TAG = "LevelStage";

    private LevelBg levelBg;

    public LevelStage(MyPaokuGame mainGame, Viewport viewport){
        super(mainGame,viewport);

        levelBg = new LevelBg(mainGame);
        addActor(levelBg);
    }

    @Override
    public void orderAct(float delta, int counter) {

    }
}
