package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public abstract class BaseEnemy extends AnimationActor{

    public static final int WALK=0;
    public static final int DEAD=1;
    public static final int HURT=2;


    public MyPaokuGame mainGame;
    public int state=-1;
    public int type;


    private Animation walkAnimation;
    private Animation hurtAnimation;

    private int deadAnimationFrameLength;

    public Vector2 position=new Vector2();

    //停止线
    private int stopX;

    public BaseEnemy(MyPaokuGame mainGame){
        this.mainGame=mainGame;
        walkAnimation=getWalkAnimation();
        hurtAnimation=getHurtAnimation();
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
        deadAnimationFrameLength=hurtAnimation.getKeyFrames().length;
    }

    public void setStopX(int stopX){
        this.stopX=stopX;
    }

    /**
     * apis
     */
    public abstract Animation getWalkAnimation();

    public abstract Animation getHurtAnimation();


    public void setType(int type){
        this.type=type;
    }

    public int getType(){
        return type;
    }

    public void walk(){
        setState(WALK);
        setCurrentAnimation(WALK);
    }

    public void hurt(){
        setState(HURT);
        setCurrentAnimation(HURT);
    }

    public void setState(int state){
        this.state=state;
    }

    public int getState(){
        return state;
    }


    public void setCurrentAnimation(int state){
        switch (state){
            case WALK:
                setAnimation(walkAnimation);
                break;
            case HURT:
                setAnimation(hurtAnimation);
                break;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        switch (this.state){
            case WALK:
                position.x-=(delta*100);
                if(position.x<stopX){
                    position.x=stopX;
                }
                setX(position.x);
                break;
            case HURT:
                position.x+=(delta*20);
                setX(position.x);
                int index=hurtAnimation.getKeyFrameIndex(getStateTime());
                if(index==(deadAnimationFrameLength-1)){
                    this.state=DEAD;
                }
                break;
            case DEAD:
                this.remove();
                break;
        }
    }
}