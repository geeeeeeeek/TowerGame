package com.xqs.mypaoku.actor.bullet;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseBullet;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/5/14 0014.
 */

public class LajiaoBullet extends BaseBullet {

    public LajiaoBullet(MyPaokuGame game, float positionX, float positionY, World world){
        super(game,positionX,positionY,world);

        body.applyLinearImpulse(new Vector2(-80f, 0), body.getWorldCenter(), true);
        body.setGravityScale(0); // 无重力
    }

    @Override
    public Animation getFlyAnimation() {
        TextureAtlas.AtlasRegion region = game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_LAJIAO_FLY);
        Animation animation = new Animation(0.1F, TextureUtil.getTextureRegions(region, 1, 4));
        return animation;
    }

    @Override
    public Animation getExplodeAnimation() {
        TextureAtlas.AtlasRegion region = game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_TWO_EXPLODE);
        Animation animation = new Animation(0.1F, TextureUtil.getTextureRegions(region, 1, 4));
        return animation;
    }

    @Override
    public int getBulletType() {
        return LAJIAO;
    }

}