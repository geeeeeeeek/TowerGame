package com.xqs.mypaoku.actor.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseBullet;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.Box2DManager;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class PlayerBullet extends BaseBullet {

    public static final int EXPLODE_Y = 150;

    public PlayerBullet(MyPaokuGame game, int screenX, int screenY, float positionX, float positionY, World world) {
        super(game, screenX, screenY, positionX, positionY, world);


        mClickX = (int) (mClickX - positionX);

        // 弹射运动方程
        // y = viy • t + 0.5 • ay • t2
        // x = vix • t + 0.5 • ax • t2

        int dy = (int) (positionY - EXPLODE_Y);

        float t = (float) Math.sqrt(dy / (0.5 * Box2DManager.Gravity));

        float vx = mClickX / t;

        body.applyLinearImpulse(new Vector2(vx, 0), body.getWorldCenter(), true);

        setScale(0.5f);
    }

    @Override
    public Animation getFlyAnimation() {
        TextureAtlas.AtlasRegion region = game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_FLY);
        Animation animation = new Animation(0.1F, TextureUtil.getTextureRegions(region, 1, 1));
        return animation;
    }

    @Override
    public Animation getExplodeAnimation() {
        TextureAtlas.AtlasRegion region = game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_EXPLODE);
        Animation animation = new Animation(0.1F, TextureUtil.getTextureRegions(region, 1, 3));
        return animation;
    }

    @Override
    public int getBulletType() {
        return PLAYER;
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        switch (state) {
            case FLY:
                setRotation(degree);
                if (position.y < EXPLODE_Y) {
                    explode();
                }
                break;
            case EXPLODE:
                int explodeKeyFrameIndex = explodeAnimation.getKeyFrameIndex(getStateTime());
                if (explodeKeyFrameIndex == (explodeAnimationFrameLength - 1)) {
                    this.state = DEAD;
                }
                setRotation(0);
                break;
            case DEAD:
                remove();
                break;
        }
    }
}
