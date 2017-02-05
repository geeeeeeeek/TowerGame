package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/1/21 0021.
 */

public class RoadActor extends BaseImageActor{

    private MyPaokuGame mainGame;


    public RoadActor(MyPaokuGame mainGame) {
        super(mainGame);

        this.mainGame=mainGame;

        TextureAtlas.AtlasRegion region=this.mainGame.getAtlas().findRegion("road");

        setRegion(region);

        setPosition(650,0);
    }
}
