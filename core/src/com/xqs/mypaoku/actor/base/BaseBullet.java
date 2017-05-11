package com.xqs.mypaoku.actor.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.Box2DManager;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public abstract class BaseBullet extends AnimationActor {
    public static final String TAG = "Bullet";

    public static final int FLY = 0;
    public static final int EXPLODE = 1;
    public static final int DEAD = 2;

    public static final int PLAYER_ONE = 10;
    public static final int PLAYER_TWO = 11;
    
    public static final int CAIHUA = 1;
    public static final int PLANE = 2;
    public static final int PLAYER2 = 3;
    public static final int YANGCONG = 4;


    //子弹位置
    public Vector2 position = new Vector2();

    //速率
    public Vector2 velocity = new Vector2();

    //上次位置
    public Vector2 lastPos = new Vector2();

    //距离
    public Vector2 disVel = new Vector2();

    //参数
    private float maxVelocity;

    //子弹状态
    public int state = -1;

    public MyPaokuGame game;

    // 子弹类型
    private int bulletType;


    public Animation flyAnimation;
    public Animation explodeAnimation;

    // 爆炸帧数
    public int explodeAnimationFrameLength;

    // 角度
    public float degree;

    public int mClickX;

    public int mClickY;

    public Body body;


    public BaseBullet(MyPaokuGame game, float positionX, float positionY, World world) {
        this(game, 0, 0, positionX, positionY, world);
    }

    public BaseBullet(MyPaokuGame game, int clickX, int clickY, float positionX, float positionY, World world) {
        this.game = game;

        bulletType = getBulletType();

        state = FLY;

        // load res
        flyAnimation = getFlyAnimation();
        explodeAnimation = getExplodeAnimation();

        setAnimation(flyAnimation);
        flyAnimation.setPlayMode(Animation.PlayMode.LOOP);
        explodeAnimationFrameLength = explodeAnimation.getKeyFrames().length;

        position.x = positionX;
        position.y = positionY;

        // 初始化上次向量
        lastPos.x = position.x;
        lastPos.y = position.y;

        this.mClickX = clickX;
        this.mClickY = clickY;

        float ratio = (game.getWorldWidth()) / Gdx.graphics.getWidth();

        this.mClickX = (int) (this.mClickX * ratio);

        // x速率
        body = Box2DManager.createBody(world, position.x, position.y);
        Fixture fixture = Box2DManager.createFixture(body);
        fixture.setUserData(this);
        body.setUserData(this);

        // 位置
        setPosition(position.x, position.y);
    }

    // apis
    public abstract Animation getFlyAnimation();

    public abstract Animation getExplodeAnimation();

    public abstract int getBulletType();


    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }


    public void explode() {
        if (getState() == FLY) {
            setState(EXPLODE);
            setAnimation(explodeAnimation);
            setScale(1f);
        }
    }


    @Override
    public void act(float delta) {
        super.act(delta);



        position.x = this.getX();
        position.y = this.getY();

        //计算差值
        disVel.y = position.y - lastPos.y;
        disVel.x = position.x - lastPos.x;

        //计算角度
        degree = MathUtils.atan2(disVel.y, disVel.x) / MathUtils.PI * 180;


        //记住坐标
        lastPos.x = position.x;
        lastPos.y = position.y;

        //出界判断
        if (position.x > game.getWorldWidth() || position.x < -getWidth() || position.y > game.getWorldHeight() || position.y < -getHeight()) {
            this.remove();
            setState(DEAD);
        }

        // 状态
        switch (state) {
            case FLY:

                break;
            case EXPLODE:
                int explodeKeyFrameIndex = explodeAnimation.getKeyFrameIndex(getStateTime());
                if (explodeKeyFrameIndex == (explodeAnimationFrameLength - 1)) {
                    this.state = DEAD;
                }
                break;
            case DEAD:
                remove();
                break;
        }

    }

}
