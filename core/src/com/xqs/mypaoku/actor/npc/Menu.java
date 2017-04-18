package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.GameState;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class Menu extends BaseImageActor{

    public static final String TAG = "Menu";
    public static final String PICTURE_BTN_MENU = "images/btn_menu.png";


    private Texture texture;
    private TextureRegion region;

    public Menu(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(PICTURE_BTN_MENU));

        region=new TextureRegion(texture);

        setRegion(region);

        setPosition(mainGame.getWorldWidth()/2-region.getRegionWidth()/2+80,mainGame.getWorldHeight()/2-region.getRegionHeight()/2);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getMainGame().showLevelScreen();
            }
        });
    }
}
