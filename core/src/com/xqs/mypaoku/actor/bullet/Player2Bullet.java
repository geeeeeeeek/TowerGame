package com.xqs.mypaoku.actor.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseBullet;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/3/28 0028.
 */


public class Player2Bullet extends BaseBullet {
    private float localDegree;

    public Player2Bullet(MyPaokuGame game, int screenX, int screenY, float positionX, float positionY, World world){
        super(game, screenX, screenY, positionX, positionY, world);


        // 转换坐标
        float widthRatio = (game.getWorldWidth()) / Gdx.graphics.getWidth();
        float heightRatio = (game.getWorldHeight()) / Gdx.graphics.getHeight();

        float worldScreenX=screenX*widthRatio;
        float worldScreenY=(Gdx.graphics.getHeight()-screenY)*heightRatio;

        float dy=worldScreenY-positionY;
        float dx=worldScreenX-positionX;

        localDegree = MathUtils.atan2(dy, dx) / MathUtils.PI * 180;

        float ratio=dy/dx;


        body.setGravityScale(0);
        body.applyLinearImpulse(new Vector2(80,80*ratio), body.getWorldCenter(), true);

    }

    @Override
    public Animation getFlyAnimation() {
        TextureAtlas.AtlasRegion region = game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_FLY);
        Animation animation = new Animation(0.1F, TextureUtil.getTextureRegions(region, 1, 2));
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
        return PLAYER2;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (state) {
            case FLY:
                if(degree==0.0) {
                    setRotation(localDegree);
                }else {
                    setRotation(degree);
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
