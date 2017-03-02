package com.xqs.mypaoku.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class Player extends AnimationActor {

    public static final int READY=0;
    public static final int DEATH=1;

    public MyPaokuGame mainGame;

    private TextureAtlas.AtlasRegion shootRegion;

    private Animation shootAnimation;

    public int state;

    public Player(MyPaokuGame mainGame) {
        this.mainGame=mainGame;

        shootRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_PLAYER_SHOOT);

        // 射击动画
        shootAnimation = new Animation(0.1F, TextureUtil.getTextureRegions(shootRegion,1,5));


        // 动画循环播放
        shootAnimation.setPlayMode(Animation.PlayMode.LOOP);
        // 设置动画
        setAnimation(shootAnimation);

        setPosition(100,290);

    }

    public void setPlayerState(int state){
        this.state=state;
    }

    public int getPlayerState(){
        return state;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
