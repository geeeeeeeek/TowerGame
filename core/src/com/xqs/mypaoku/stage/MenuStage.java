package com.xqs.mypaoku.stage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.MenuBg;
import com.xqs.mypaoku.stage.base.BaseStage;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class MenuStage extends BaseStage {
    public static final String TAG = "MenuStage";
    public MenuStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);

        MenuBg menuBg = new MenuBg(mainGame);
        addActor(menuBg);
    }

    @Override
    public void orderAct(float delta, int counter) {

    }
}
