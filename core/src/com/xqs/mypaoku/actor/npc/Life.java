package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.res.Res;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class Life extends BaseImageActor {


    private TextureAtlas.AtlasRegion region;

    public Life(MyPaokuGame mainGame) {
        super(mainGame);

        region=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_PLAYER_LIFE);

        setRegion(region);


    }
}
