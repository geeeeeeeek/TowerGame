package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector2;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.MyAnimation;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.TextureUtil;
import com.xqs.mypaoku.util.Util;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class Enemy extends AnimationActor {

    public static final int STOP_X=230;

    public static final int positionY=120;

    public static final int WALK=0;

    public static final int DEAD=1;

    public static final int HURT=2;

    private int state=-1;

    private Vector2 position=new Vector2();

    public MyPaokuGame mainGame;

    private TextureAtlas.AtlasRegion walkRegion;

    private TextureAtlas.AtlasRegion deadRegion;

    private int deadAnimationFrameLength;

    private  Animation walkAnimation;

    private  Animation hurtAnimation;



    public Enemy(MyPaokuGame mainGame) {

        this.mainGame=mainGame;


        walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_DACONG_WALK);
        deadRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_DACONG_DEAD);



        // 行走动画
        walkAnimation = new  Animation(0.1F, TextureUtil.getTextureRegions(walkRegion,1,6));

        // 死亡动画
        hurtAnimation = new  Animation(0.1F, TextureUtil.getTextureRegions(deadRegion,1,6));

        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);

        deadAnimationFrameLength=hurtAnimation.getKeyFrames().length;





        setState(WALK);

        position.x=this.mainGame.getWorldWidth();
        position.y=120;

        setPosition(position.x,position.y);
    }


    /**
     * 设置状态和动画
     * @param state
     */
    public void setState(int state){
        this.state=state;
        setCurrentAnimation(state);
    }

    public int getState(){
        return state;
    }

    @Override
    public void act(float delta) {

        if(GameStage.gameState== GameState.pause){
            return;
        }

        super.act(delta);



        switch (this.state){
            case WALK:
                position.x-=(delta*100);
                if(position.x<STOP_X){
                    position.x=STOP_X;
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

    public void setCurrentAnimation(int st){
        switch (st){
            case WALK:
                setAnimation(walkAnimation);
                break;
            case HURT:
                setAnimation(hurtAnimation);
                break;
        }
    }
}
