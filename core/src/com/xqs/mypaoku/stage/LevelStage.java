package com.xqs.mypaoku.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.level.BackButton;
import com.xqs.mypaoku.actor.level.LevelBg;
import com.xqs.mypaoku.actor.level.LevelIcon;
import com.xqs.mypaoku.res.Constant;
import com.xqs.mypaoku.stage.base.BaseStage;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class LevelStage extends BaseStage {
    public static final String TAG = "LevelStage";

    private LevelBg levelBg;
    private BackButton backButton;

    private LevelIcon levelIcon01;
    private LevelIcon levelIcon02;
    private LevelIcon levelIcon03;

    public LevelStage(MyPaokuGame mainGame, Viewport viewport){
        super(mainGame,viewport);

        levelBg = new LevelBg(mainGame);
        addActor(levelBg);

        backButton = new BackButton(mainGame);
        addActor(backButton);

        initLevelIcon();

        setClick();
    }

    private void initLevelIcon() {
        levelIcon01 = new LevelIcon(getMainGame());
        levelIcon01.setPosition(100,500);
        levelIcon02 = new LevelIcon(getMainGame());
        levelIcon02.setPosition(300,500);
        levelIcon03 = new LevelIcon(getMainGame());
        levelIcon03.setPosition(500,500);


        addActor(levelIcon01);
        addActor(levelIcon02);
        addActor(levelIcon03);
    }

    private void setClick() {
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getMainGame().showMenuScreen(Constant.SCREEN_LEVEL);
            }
        });
    }

    @Override
    public void orderAct(float delta, int counter) {

    }
}
