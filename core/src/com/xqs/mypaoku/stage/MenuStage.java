package com.xqs.mypaoku.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.MenuBg;
import com.xqs.mypaoku.actor.QuitButton;
import com.xqs.mypaoku.actor.SoundButton;
import com.xqs.mypaoku.actor.StartButton;
import com.xqs.mypaoku.stage.base.BaseStage;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class MenuStage extends BaseStage {
    public static final String TAG = "MenuStage";

    private MenuBg menuBg;
    private SoundButton soundButton;
    private StartButton startButton;
    private QuitButton quitButton;


    public MenuStage(MyPaokuGame mainGame, Viewport viewport) {
        super(mainGame, viewport);

        // bg
        menuBg = new MenuBg(mainGame);
        addActor(menuBg);

        // sound btn
        soundButton = new SoundButton(mainGame);
        addActor(soundButton);

        // start btn
        startButton = new StartButton(mainGame);
        addActor(startButton);

        // quit btn
        quitButton = new QuitButton(mainGame);
        addActor(quitButton);

        // set listeners
        soundButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                soundButton.press();
            }
        });

        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Util.log(TAG,"--> press start");
            }
        });

    }

    @Override
    public void orderAct(float delta, int counter) {
    }


    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act() {
        super.act();
    }
}
