package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class SplashActor extends BaseImageActor {
    public static String SPLASH_PICTURE="images/splash.png";

    private Texture texture;
    private TextureRegion region;

    public SplashActor(MyPaokuGame mainGame) {
        super(mainGame);
        texture = new Texture(Gdx.files.internal(SPLASH_PICTURE));

        region=new TextureRegion(texture);

        setRegion(region);
    }
}
