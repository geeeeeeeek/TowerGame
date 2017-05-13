package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/2 0002.
 */

public class Pause extends BaseImageActor {
    public static final String TAG = "Pause";

    public static String PICTURE="images/icon_pause.png";

    private Texture texture;
    private TextureRegion region;

    public Pause(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(PICTURE));

        region=new TextureRegion(texture);

        setRegion(region);

        setScale(0.8f);

        setPosition(mainGame.getWorldWidth()-60,mainGame.getWorldHeight()-60);
    }
}
