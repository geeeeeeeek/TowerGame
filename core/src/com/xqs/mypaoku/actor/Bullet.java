package com.xqs.mypaoku.actor;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
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

    public int state;

    public MyPaokuGame game;

    public int bulletType;

    public TextureAtlas.AtlasRegion region;

    public float x;

    public float y;

    public Bullet(MyPaokuGame game,int type){
        this.game=game;

        state=LIVE;

        bulletType=type;

        setType(type);

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

    float x2;

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (bulletType){
            case 0:
                x=getX()-10;
                setPosition(x,getY());
                break;
            case 1:
                x=getX()+10;
                x2=x-100;
                float a=-0.0031f;
                float b=2.79f;
                float c=-1.48f;
                y=a*x2*x2+b*x2;
                Util.log("bullet ",x+" "+y+ " "+getHeight());
                setPosition(x,y);
                break;
        }

        if(x>game.getWorldWidth()||x<-getWidth()||y>game.getWorldHeight()||y<-getHeight()){
            this.remove();
            setState(DEAD);
        }


    }
}
