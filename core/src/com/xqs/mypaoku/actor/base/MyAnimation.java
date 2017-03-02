package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class MyAnimation extends Animation {

    public String tag;

    public MyAnimation(float frameDuration, Object[] keyFrames) {
        super(frameDuration, keyFrames);
    }

    public void setTag(String tag){
        this.tag=tag;
    }

    public String getTag(){
        return tag;
    }
}
