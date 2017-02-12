package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.TextureUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class Enemy extends AnimationActor {

    public MyPaokuGame mainGame;

    public static final int WALK=0;

    public static final int DEAD=1;

    private int state=-1;

    private float x;

    private TextureAtlas.AtlasRegion region;

    private TextureAtlas.AtlasRegion deadRegion;

    private Animation walkAnimation;

    private Animation deadAnimation;


    public Enemy(MyPaokuGame mainGame) {

        this.mainGame=mainGame;


        region=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY);
        deadRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_DEAD);



        // 行走动画
        walkAnimation = new Animation(0.1F, TextureUtil.getTextureRegions(region,1,8));

        // 死亡动画
        deadAnimation = new Animation(0.6F, TextureUtil.getTextureRegions(deadRegion,1,6));

        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);

        int length=deadAnimation.getKeyFrames().length;




        setCurrentAnimation(WALK);

        x=this.mainGame.getWorldWidth();

        setPosition(x,48);
    }


    public void setState(int state){
        this.state=state;
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

//        stateTime += Gdx.graphics.getDeltaTime();

        if(x<200&&this.state==WALK){
//            setCurrentAnimation(DEAD);
        }


        switch (this.state){
            case WALK:
                x-=(delta*100);
                setX(x);
                break;
            case DEAD:

                x+=(delta*20);
                setX(x);
                int index=deadAnimation.getKeyFrameIndex(getStateTime());
                if(index==3){
                    GameStage.gameState=GameState.pause;
                }
                Gdx.app.log("index",index+"");
                break;
        }
    }

    public void setCurrentAnimation(int st){
        switch (st){
            case WALK:
                if(this.state!=WALK) {
                    setAnimation(walkAnimation);
                    this.state = WALK;
                }
                break;
            case DEAD:
                if(this.state!=DEAD) {
                    setAnimation(deadAnimation);
                    deadAnimation.setPlayMode(Animation.PlayMode.NORMAL);
                    state = DEAD;
                }
                break;
        }
    }
}
