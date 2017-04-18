package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.res.Res;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Popup extends BaseImageActor {
    public static final String TAG = "Popup";
    public static final String PICTURE_POPUP_BG = "images/popup_bg.png";


    private Texture texture;
    private TextureRegion region;

    public Popup(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(PICTURE_POPUP_BG));

        region=new TextureRegion(texture);

        setRegion(region);

        setPosition(mainGame.getWorldWidth()/2-region.getRegionWidth()/2,mainGame.getWorldHeight()/2-region.getRegionHeight()/2+50);

    }


}
