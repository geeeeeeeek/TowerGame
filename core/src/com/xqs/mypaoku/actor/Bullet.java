package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.framework.AnimationActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class Bullet extends AnimationActor{

    public static int LIVE=0;
    public static int DEAD=1;


    public static float START_X=180f;
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

    public TextureAtlas.AtlasRegion region;

    //角度
    private float degree;

    private int mClickX;

    private int mClickY;


    public Bullet(MyPaokuGame game, int type, int screenX, int screenY){
        this.game=game;

        state=LIVE;

        bulletType=type;

        setType(type);


        //y速率 公式： velocity.y-=maxVelocity*(delta);



        position.x=START_X;
        position.y=START_Y;

        //初始化上次向量
        lastPos.x=position.x;
        lastPos.y=position.y;

        this.mClickX=screenX;
        this.mClickY=screenY;

        float ratio=(game.getWorldWidth())/Gdx.graphics.getWidth();


        //x速率
//        velocity.x=this.clickX*1.36f;
        velocity.x=(this.mClickX)*ratio-START_X;

        velocity.y=velocity.x/ratio;


        maxVelocity=velocity.x*ratio+300f;




        Util.log("velocity.X",velocity.x+"");

    }

    public void setState(int state){
        this.state=state;
    }

    public int getState(){
        return state;
    }

    public void setType(int type){
        switch (type){
            case 0:
                region=game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET);
                setRegion(region);
                break;
            case 1:
                region=game.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_2);
                setRegion(region);
                break;

        }
    }




    @Override
    public void act(float delta) {
        super.act(delta);




        switch (bulletType){
            case 0:
                position.x=getX()-10;
                setPosition(position.x,getY());
                break;
            case 1:

                velocity.y-=maxVelocity*(delta);

                //最新位置
                position.x=position.x+(velocity.x*delta);
                position.y=position.y+(velocity.y*delta);


                //计算差值
                disVel.y=position.y-lastPos.y;
                disVel.x=position.x-lastPos.x;

                //计算角度
                degree=MathUtils.atan2(disVel.y,disVel.x)/MathUtils.PI*180;

                setPosition(position.x,position.y);
                setRotation(degree);

                //记住坐标
                lastPos.x=position.x;
                lastPos.y=position.y;

                break;
        }

        if(position.x>game.getWorldWidth()||position.x<-getWidth()||position.y>game.getWorldHeight()||position.y<-getHeight()){
            this.remove();
            setState(DEAD);
        }


    }

}
