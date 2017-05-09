package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class Player extends AnimationActor {
    public static final String TAG="Player";

    public static final int STANDING=1;
    public static final int SHOOTING=2;

    private int life;

    public MyPaokuGame mainGame;

    private int shootingAnimationFrameLength;

    private TextureAtlas.AtlasRegion standingRegion;
    private Animation standingAnimation;

    private TextureAtlas.AtlasRegion shootRegion;
    private Animation shootAnimation;

    public int state;

    public Player(MyPaokuGame mainGame) {
        this.mainGame=mainGame;

        standingRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_PLAYER_STANDING);
        standingAnimation = new Animation(0.8F, TextureUtil.getTextureRegions(standingRegion,1,2));

        shootRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_PLAYER_SHOOT);
        shootAnimation = new Animation(0.1F, TextureUtil.getTextureRegions(shootRegion,1,5));

        shootingAnimationFrameLength=shootAnimation.getKeyFrames().length;


        // 动画循环播放
        standingAnimation.setPlayMode(Animation.PlayMode.LOOP);
        // 设置动画
        setAnimation(standingAnimation);

        setPosition(100,290);
    }

    public void setLife(int life){
        this.life=life;
    }

    public int getLife(){
        return this.life;
    }

    public void minusLife(){
        life--;
        if(life<=0){
            life=0;
        }
        Gdx.app.log(TAG,"Life="+life);
        GameStage.getInstance(mainGame).updateLifes();
    }

    public void shoot(){
        setState(SHOOTING);
        setCurrentAnimation(SHOOTING);
        SoundHelper.playFireSound();
    }


    public void setCurrentAnimation(int state){
        switch (state){
            case STANDING:
                setAnimation(standingAnimation);
                break;
            case SHOOTING:
                setAnimation(shootAnimation);
                break;
        }
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState(){
        return state;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (this.state){
            case STANDING:
                break;
            case SHOOTING:
                int fireKeyFrameIndex=shootAnimation.getKeyFrameIndex(getStateTime());
                if(fireKeyFrameIndex==(shootingAnimationFrameLength-1)){
                    setState(STANDING);
                    setCurrentAnimation(STANDING);
                }
                break;
        }
    }
}
