package com.xqs.mypaoku.actor.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.Tower;
import com.xqs.mypaoku.actor.base.BaseEnemy;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class HuangguaEnemy extends BaseEnemy {

    public final static String TAG="HuangguaEnemy";
    public final static int StartY=150;

    public HuangguaEnemy(MyPaokuGame mainGame) {
        super(mainGame);
        position.x=this.mainGame.getWorldWidth();
        position.y=StartY;

        setStopX(Tower.getStopX());

        setPosition(position.x,position.y);

        walk();
    }

    @Override
    public Animation getWalkAnimation() {
        TextureAtlas.AtlasRegion walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_HUANGGUA_WALK);
        Animation walkAnimation = new  Animation(0.2F, TextureUtil.getTextureRegions(walkRegion,1,2));
        return walkAnimation;
    }

    @Override
    public Animation getFireAnimation() {
        return getWalkAnimation();
    }

    @Override
    public Animation getHurtAnimation() {
        TextureAtlas.AtlasRegion region=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_HUANGGUA_DEAD);
        Animation animation = new  Animation(0.2F, TextureUtil.getTextureRegions(region,1,4));
        return animation;
    }

    @Override
    public void orderAct(float delta, int counter) {

    }
}
