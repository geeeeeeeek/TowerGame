package com.xqs.mypaoku.actor.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector2;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.Tower;
import com.xqs.mypaoku.actor.base.BaseEnemy;
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

public class DacongEnemy extends BaseEnemy {

    public final static int StartY=150;


    public DacongEnemy(MyPaokuGame mainGame) {
        super(mainGame);



        position.x=mainGame.getWorldWidth();
        position.y=StartY;

        setStopX(Tower.getStopX());

        setPosition(position.x,position.y);

        walk();
    }


    @Override
    public Animation getWalkAnimation() {
        TextureAtlas.AtlasRegion walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_DACONG_WALK);
        Animation walkAnimation = new  Animation(0.1F, TextureUtil.getTextureRegions(walkRegion,1,6));
        return walkAnimation;
    }

    @Override
    public Animation getFireAnimation() {
        return null;
    }

    @Override
    public Animation getHurtAnimation() {
        TextureAtlas.AtlasRegion deadRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_DACONG_DEAD);
        Animation hurtAnimation = new  Animation(0.1F, TextureUtil.getTextureRegions(deadRegion,1,6));
        return hurtAnimation;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void orderAct(float delta, int counter) {

    }
}
