package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.SoundHelper;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.Box2DManager;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/5/6 0006.
 */

public abstract class BasePlayerBullet extends BaseBullet {

    public static final int EXPLODE_Y = 150;

    public BasePlayerBullet(MyPaokuGame game, int clickX, int clickY, float positionX, float positionY, World world) {
        super(game, clickX, clickY, positionX, positionY, world);


        mClickX = (int) (mClickX - positionX);

        // 弹射运动方程
        // y = viy • t + 0.5 • ay • t2
        // x = vix • t + 0.5 • ax • t2

        int dy = (int) (positionY - EXPLODE_Y);

        float t = (float) Math.sqrt(dy / (0.5 * Box2DManager.Gravity));

        float vx = mClickX / t;

        body.applyLinearImpulse(new Vector2(vx, 0), body.getWorldCenter(), true);

        setScale(0.8f);
    }

    @Override
    public Animation getFlyAnimation() {
        return getPlayerFlyAnimation();
    }

    @Override
    public Animation getExplodeAnimation() {
        return getPlayerExplodeAnimation();
    }

    @Override
    public int getBulletType() {
        return getPlayerBulletType();
    }


    @Override
    public void act(float delta) {

        super.act(delta);

        switch (state) {
            case FLY:
                setRotation(degree);
                if (position.y < EXPLODE_Y) {
                    explode();
                    SoundHelper.playExplodeSound();
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


    // apis
    public abstract Animation getPlayerFlyAnimation();
    public abstract Animation getPlayerExplodeAnimation();
    public abstract int getPlayerBulletType();
}
