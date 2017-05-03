package com.xqs.mypaoku.actor.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.Tower;
import com.xqs.mypaoku.actor.base.BaseEnemy;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class WoniuEnemy extends BaseEnemy {
    public final static String TAG="WoniuEnemy";

    public final static int StartY=150;

    public WoniuEnemy(MyPaokuGame mainGame) {
        super(mainGame);
        this.mainGame=mainGame;

        position.x=this.mainGame.getWorldWidth();
        position.y=StartY;

        setStopX(Tower.getStopX());

        setPosition(position.x,position.y);

        setScale(0.5f);

        walk();
    }

    @Override
    public Animation getWalkAnimation() {
        TextureAtlas.AtlasRegion walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_WONIU_WALK);
        Animation walkAnimation = new  Animation(0.06F, TextureUtil.getTextureRegions(walkRegion,1,8));
        return walkAnimation;
    }

    @Override
    public Animation getFireAnimation() {
        return getWalkAnimation();
    }

    @Override
    public Animation getHurtAnimation() {
        TextureAtlas.AtlasRegion walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_WONIU_DEAD);
        Animation walkAnimation = new  Animation(0.08F, TextureUtil.getTextureRegions(walkRegion,1,5));
        return walkAnimation;
    }

    @Override
    public void orderAct(float delta, int counter) {
        if(counter%20==0) {
            // check if enemy was close to tower
            if(getState() == WALK && getX()<=getStopX()){
                GameStage.getInstance(mainGame).minusLife();
            }
        }
    }
}
