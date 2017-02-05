package com.xqs.mypaoku.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;

/**
 * Created by Administrator on 2017/1/17 0017.
 */

public class PlayerActor extends AnimationActor {

    public static final int READY=0;
    public static final int DEATH=1;

    public MyPaokuGame mainGame;

    public int state;

    public PlayerActor(MyPaokuGame mainGame) {
        this.mainGame=mainGame;

        // 创建动画
        Animation animation = new Animation(
                0.2F,
                this.mainGame.getAtlas().findRegions(Res.Atlas.IMAGE_BIRD_YELLOW_01_TO_03)
        );
        // 动画循环播放
        animation.setPlayMode(Animation.PlayMode.LOOP);
        // 设置动画
        setAnimation(animation);

        setPosition(100,100);

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
