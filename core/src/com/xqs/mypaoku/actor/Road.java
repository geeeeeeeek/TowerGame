package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/1/21 0021.
 */

public class Road extends BaseImageActor{

    private MyPaokuGame mainGame;

    private float time=0;


    public Road(MyPaokuGame mainGame) {
        super(mainGame);

        this.mainGame=mainGame;

        TextureAtlas.AtlasRegion region=this.mainGame.getAtlas().findRegion("road");

        setRegion(region);

        setPosition(650,0);


        this.clear();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        time+=delta;

    }


}
