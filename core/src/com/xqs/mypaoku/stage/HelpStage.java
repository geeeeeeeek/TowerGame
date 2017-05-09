package com.xqs.mypaoku.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.level.BackButton;
import com.xqs.mypaoku.actor.menu.MenuBg;
import com.xqs.mypaoku.actor.npc.HelpPopup;
import com.xqs.mypaoku.res.Constant;
import com.xqs.mypaoku.stage.base.BaseStage;

import java.time.format.TextStyle;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class HelpStage extends BaseStage {

    private MenuBg menuBg;

    private BackButton backButton;

    private HelpPopup helpPopup;

    public HelpStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);

        menuBg = new MenuBg(getMainGame());
        backButton = new BackButton(getMainGame());
        helpPopup = new HelpPopup(getMainGame());

        addActor(menuBg);
        addActor(backButton);
        addActor(helpPopup);


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
