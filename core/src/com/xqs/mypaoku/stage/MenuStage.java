package com.xqs.mypaoku.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.SoundHelper;
import com.xqs.mypaoku.actor.menu.FacebookButton;
import com.xqs.mypaoku.actor.menu.HelpButton;
import com.xqs.mypaoku.actor.menu.MenuBg;
import com.xqs.mypaoku.actor.menu.QuitButton;
import com.xqs.mypaoku.actor.menu.SoundButton;
import com.xqs.mypaoku.actor.menu.StartButton;
import com.xqs.mypaoku.actor.npc.HelpPopup;
import com.xqs.mypaoku.stage.base.BaseStage;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class MenuStage extends BaseStage {
    public static final String TAG = "MenuStage";

    private MenuBg menuBg;
    private SoundButton soundButton;
    private StartButton startButton;
    private HelpButton helpButton;
    private QuitButton quitButton;
    private FacebookButton facebookButton;


    public MenuStage(final MyPaokuGame mainGame, Viewport viewport) {
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

        // help btn
        helpButton = new HelpButton(mainGame);
        addActor(helpButton);

        // quit btn
        quitButton = new QuitButton(mainGame);
        addActor(quitButton);


        // facebook share
        facebookButton = new FacebookButton(mainGame);
        addActor(facebookButton);

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
                mainGame.showLevelScreen();
                SoundHelper.playButtonSound();
            }
        });

        helpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mainGame.showHelpScreen();
                SoundHelper.playButtonSound();
            }
        });

        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                quitButton.setChecked(true);
//                mainGame.dispose();
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void orderAct(float delta, int counter) {
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
