package com.xqs.mypaoku.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Administrator on 2017/3/3 0003.
 */

public class Box2DManager {

    public static final float TIME_STEP=1/60f;
    public static final int VELOCITY_ITERATIONS=6;
    public static final int POSITION_ITERATIONS=2;

    public static World createWorld(){

        World world = new World(new Vector2(0, 0), true);

        return world;

    }

    public static Body createBody(World world,float positionX,float positionY){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX,positionY);


        Body body = world.createBody(bodyDef);

        return body;

    }

    public static Fixture createFixture(Body body){
        CircleShape circle = new CircleShape();
        circle.setRadius(4f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor=true;
        fixtureDef.shape = circle;
        fixtureDef.density = .0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        Fixture fixture = body.createFixture(fixtureDef);
        return fixture;
    }

    static float ct=0;
    public static void doPhysicsStep(World world){
        for(int i=0;i<6;i++) {
            world.step(Box2DManager.TIME_STEP, Box2DManager.VELOCITY_ITERATIONS, Box2DManager.POSITION_ITERATIONS);
        }
        ct+=TIME_STEP;
    }


}
