package com.xqs.mypaoku.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.MenuBg;
import com.xqs.mypaoku.actor.SoundButton;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class MenuStage extends BaseStage {
    public static final String TAG = "MenuStage";

    private MenuBg menuBg;
    private SoundButton soundButton;


    public MenuStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);

        // bg
        menuBg = new MenuBg(mainGame);
        addActor(menuBg);

        // sound btn
        soundButton = new SoundButton(mainGame);
        addActor(soundButton);

        // set listeners
        soundButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                soundButton.press();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

    }

    @Override
    public void orderAct(float delta, int counter) {
    }

}
