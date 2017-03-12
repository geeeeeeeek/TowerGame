package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.Box2DManager;
import com.xqs.mypaoku.util.TextureUtil;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class Bullet extends AnimationActor{
    public final static String TAG="Bullet";

    public final static int FLY=0;
    public final static int EXPLODE=1;
    public final static int DEAD=2;

    public final static int PLAYER=0;
    public final static int CAIHUA=1;


    public static float START_X=100f;
    public static float START_Y=300f;

    //子弹位置
    public Vector2 position=new Vector2();

    //速率
    public Vector2 velocity=new Vector2();

    //上次位置
    public Vector2 lastPos=new Vector2();

    //距离
    public Vector2 disVel=new Vector2();

    //参数
    private float maxVelocity;

    //子弹状态
    public int state;

    public MyPaokuGame game;

    //子弹类型
    public int bulletType;

    public TextureAtlas.AtlasRegion flyRegion;
    public TextureAtlas.AtlasRegion explodeRegion;

    private Animation flyAnimation;
    private Animation explodeAnimation;

    private int explodeAnimationFrameLength;

    //角度
    private float degree;

    private int mClickX;

    private int mClickY;

    Body body;


    public Bullet(MyPaokuGame game, int type, int screenX, int screenY,float positionX,float positionY, World world){
        this.game=game;

        state=FLY;

        bulletType=type;

        setType(type);


        position.x=positionX;
        position.y=positionY;

        //初始化上次向量
        lastPos.x=position.x;
        lastPos.y=position.y;

        this.mClickX=screenX;
        this.mClickY=screenY;

        float ratio=(game.getWorldWidth())/Gdx.graphics.getWidth();


        //x速率

        body= Box2DManager.createBody(world,position.x,position.y);
        Fixture fixture=Box2DManager.createFixture(body);
        fixture.setUserData(this);
        body.setUserData(this);

        switch (type){
            case PLAYER:
                body.applyLinearImpulse(new Vector2((this.mClickX * ratio - 100) / 14, -9.8f), body.getWorldCenter(), true);
                break;
            case CAIHUA:
                body.applyLinearImpulse(new Vector2(-80f, 0), body.getWorldCenter(), true);
                break;
        }

        setPosition(position.x,position.y);


    }

    public Bullet(MyPaokuGame game, int type,float positionX,float positionY, World world){
        this(game,type,0,0,positionX,positionY,world);
    }

    public void setState(int state){
        this.state=state;
    }

    public int getState(){
        return state;
    }

    public void setType(int type){
        switch (type){
            case Bullet.PLAYER:
                flyRegion=game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_FLY);
                explodeRegion=game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_EXPLODE);

                flyAnimation= new Animation(0.1F, TextureUtil.getTextureRegions(flyRegion,1,2));
                explodeAnimation=new Animation(0.1f,TextureUtil.getTextureRegions(explodeRegion,1,3));
                flyAnimation.setPlayMode(Animation.PlayMode.LOOP);
                explodeAnimationFrameLength=explodeAnimation.getKeyFrames().length;
                setAnimation(flyAnimation);
                break;
            case Bullet.CAIHUA:
                flyRegion=game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_TWO_FLY);
                explodeRegion=game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_EXPLODE);

                flyAnimation= new Animation(0.1F, TextureUtil.getTextureRegions(flyRegion,1,4));
                explodeAnimation=new Animation(0.1f,TextureUtil.getTextureRegions(explodeRegion,1,3));
                flyAnimation.setPlayMode(Animation.PlayMode.LOOP);
                explodeAnimationFrameLength=explodeAnimation.getKeyFrames().length;
                setAnimation(flyAnimation);
                break;

        }
    }

    public void explode(){
        if(getState()==FLY) {
            setState(EXPLODE);
            setAnimation(explodeAnimation);
        }
    }




    @Override
    public void act(float delta) {
        super.act(delta);

        position.x=this.getX();
        position.y=this.getY();

        switch (bulletType){
            case Bullet.PLAYER:
                switch (state){
                    case FLY:
                        if(position.y<100){
                            explode();
                        }

                        //计算差值
                        disVel.y=position.y-lastPos.y;
                        disVel.x=position.x-lastPos.x;

                        //计算角度
                        degree= MathUtils.atan2(disVel.y,disVel.x)/MathUtils.PI*180;

                        setRotation(degree);

                        //记住坐标
                        lastPos.x=position.x;
                        lastPos.y=position.y;

                        break;
                    case EXPLODE:
                        int explodeKeyFrameIndex=explodeAnimation.getKeyFrameIndex(getStateTime());
                        if(explodeKeyFrameIndex==(explodeAnimationFrameLength-1)){
                            this.state=DEAD;
                        }
                        break;
                    case DEAD:
                        remove();
                        break;
                }
                break;
            case Bullet.CAIHUA:
                switch (state){
                    case FLY:


                        break;
                    case EXPLODE:
                        int explodeKeyFrameIndex=explodeAnimation.getKeyFrameIndex(getStateTime());
                        if(explodeKeyFrameIndex==(explodeAnimationFrameLength-1)){
                            this.state=DEAD;
                        }
                        break;
                    case DEAD:
                        remove();
                        break;
                }
                break;
        }

        //出界判断
        if(position.x>game.getWorldWidth()||position.x<-getWidth()||position.y>game.getWorldHeight()||position.y<-getHeight()){
            this.remove();
            setState(DEAD);
        }


    }

}
