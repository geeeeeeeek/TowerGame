package com.xqs.mypaoku.actor.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class LevelBg extends BaseImageActor {
    public static final String TAG = "LevelBg";

    public static String PICTURE_LEVEL="images/bg01.png";

    private Texture texture;
    private TextureRegion region;
    public LevelBg(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(PICTURE_LEVEL));

        region=new TextureRegion(texture);

        setRegion(region);
    }
}
