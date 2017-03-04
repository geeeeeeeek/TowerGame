package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.math.Vector2;
import com.xqs.mypaoku.actor.framework.AnimationActor;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public class BaseEnemy extends AnimationActor{

    public static final int WALK=0;
    public static final int DEAD=1;
    public static final int HURT=2;

    public Vector2 position=new Vector2();


}
