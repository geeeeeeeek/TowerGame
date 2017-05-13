package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.res.Res;

import java.util.Iterator;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class Life extends BaseImageActor {

    public static String PICTURE="images/life.png";

    private Texture texture;
    private TextureRegion region;

    public Life(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(PICTURE));

        region=new TextureRegion(texture);

        setRegion(region);

        setScale(0.4f);
    }


}
