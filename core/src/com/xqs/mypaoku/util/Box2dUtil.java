package com.xqs.mypaoku.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Administrator on 2017/3/3 0003.
 */

public class Box2DUtil {

    public static World createWorld(){

        World world = new World(new Vector2(0, -10), true);

        return world;

    }

    public static Body createBody(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, 300);

        Body body = world.createBody(bodyDef);

        return body;

//        CircleShape circle = new CircleShape();
//        circle.setRadius(6f);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = circle;
//        fixtureDef.density = 0.5f;
//        fixtureDef.friction = 0.4f;
//        fixtureDef.restitution = 0.6f; // Make it bounce a little bit
//
//        Fixture fixture = body.createFixture(fixtureDef);
//
//        circle.dispose();
    }
}
