package com.xqs.mypaoku.actor.bullet;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BasePlayerBullet;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/5/6 0006.
 */

public class PlayerOneBullet extends BasePlayerBullet {

    public PlayerOneBullet(MyPaokuGame game, int clickX, int clickY, float positionX, float positionY, World world) {
        super(game, clickX, clickY, positionX, positionY, world);
    }

    @Override
    public Animation getPlayerFlyAnimation() {
        TextureAtlas.AtlasRegion region = game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_FLY);
        Animation animation = new Animation(0.1F, TextureUtil.getTextureRegions(region, 1, 1));
        return animation;
    }

    @Override
    public Animation getPlayerExplodeAnimation() {
        TextureAtlas.AtlasRegion region = game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_EXPLODE);
        Animation animation = new Animation(0.1F, TextureUtil.getTextureRegions(region, 1, 3));
        return animation;
    }

    @Override
    public int getPlayerBulletType() {
        return PLAYER_ONE;
    }


}
