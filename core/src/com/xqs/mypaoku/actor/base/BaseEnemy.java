package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public abstract class BaseEnemy extends AnimationActor{
    public final static String TAG="BaseEnemy";

    public static final int WALK=0;
    public static final int FIRE=1;
    public static final int DEAD=2;
    public static final int HURT=3;


    public int state=-1;

    public MyPaokuGame mainGame;

    public int type;

    private Animation walkAnimation;
    private Animation fireAnimation;
    private Animation hurtAnimation;

    private int fireAnimationFrameLength;
    private int deadAnimationFrameLength;

    public Vector2 position=new Vector2();

    //停止线
    private int stopX;

    /**计数相关**/
    private float timeCounter=0f;
    private float next=0f;
    private int counter=0;

    public BaseEnemy(MyPaokuGame mainGame){
        this.mainGame=mainGame;
        walkAnimation=getWalkAnimation();
        fireAnimation=getFireAnimation();
        hurtAnimation=getHurtAnimation();
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
        if(null!=fireAnimation){
            fireAnimationFrameLength=fireAnimation.getKeyFrames().length;
        }
        deadAnimationFrameLength=hurtAnimation.getKeyFrames().length;
    }

    public void setStopX(int stopX){
        this.stopX=stopX;
    }

    /**
     * apis
     */
    public abstract Animation getWalkAnimation();
    public abstract Animation getFireAnimation();
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

    public void fire(){
        if(state==WALK) {
            setState(FIRE);
            setCurrentAnimation(FIRE);
        }
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
            case FIRE:
                setAnimation(fireAnimation);
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
                break;
            case FIRE:
                position.x-=(delta*100);
                if(position.x<stopX){
                    position.x=stopX;
                }
                int fireKeyFrameIndex=fireAnimation.getKeyFrameIndex(getStateTime());
                if(fireKeyFrameIndex==(fireAnimationFrameLength-1)){
                    walk();
                }
                break;
            case HURT:
                position.x+=(delta*20);
                int hurtKeyFrameIndex=hurtAnimation.getKeyFrameIndex(getStateTime());
                if(hurtKeyFrameIndex==(deadAnimationFrameLength-1)){
                    this.state=DEAD;
                }
                break;
            case DEAD:
                this.remove();
                break;
        }

        setX(position.x);

        if(getRightX()<0){
            setState(DEAD);
        }

        timeCounter+=delta;

        if((float)timeCounter>next){
            next+=0.1f;
            counter++;
            orderAct(delta,counter);
        }
    }


    //api
    public abstract void orderAct(float delta,int counter);
}
