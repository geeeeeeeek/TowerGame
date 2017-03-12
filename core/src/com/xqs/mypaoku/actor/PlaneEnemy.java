package com.xqs.mypaoku.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseEnemy;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.GameState;
import com.xqs.mypaoku.util.TextureUtil;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class PlaneEnemy extends BaseEnemy {

    public static final int MAX_Y=600;
    public static final int MIN_Y=200;

    public static final int UP=0;
    public static final int DOWN=1;

    public int mDirection=UP;


    public PlaneEnemy(MyPaokuGame mainGame) {
        super(mainGame);

        this.mainGame=mainGame;

        position.x=this.mainGame.getWorldWidth();
        position.y=MIN_Y;

        setStopX(-500);

        setPosition(position.x,position.y);

        walk();
    }


    @Override
    public Animation getWalkAnimation() {
        TextureAtlas.AtlasRegion walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_PLANE_WALK);
        Animation walkAnimation = new  Animation(0.2F, TextureUtil.getTextureRegions(walkRegion,1,2));
        return walkAnimation;
    }

    @Override
    public Animation getFireAnimation() {
        TextureAtlas.AtlasRegion walkRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_PLANE_FIRE);
        Animation fireAnimation = new  Animation(0.2F, TextureUtil.getTextureRegions(walkRegion,1,2));
        return fireAnimation;
    }

    @Override
    public Animation getHurtAnimation() {
        TextureAtlas.AtlasRegion deadRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_PLANE_DEAD);
        Animation hurtAnimation = new  Animation(0.1F, TextureUtil.getTextureRegions(deadRegion,1,6));
        return hurtAnimation;
    }

    public void fire(){
        super.fire();
//        GameStage.getInstance(mainGame).generateBullet(1,600,100,getX(),getY());
    }


    @Override
    public void act(float delta) {
        if(GameStage.gameState== GameState.pause){
            return;
        }
        super.act(delta);

        switch (mDirection){
            case UP:
                position.y+=0.8;
                if(position.y>MAX_Y){
                    mDirection=DOWN;
                    fire();
                }
                break;
            case DOWN:
                position.y-=0.8;
                if(position.y<MIN_Y){
                    mDirection=UP;
                    fire();
                }
                break;
        }
        setY(position.y);

    }

    @Override
    public void orderAct(float delta, int counter) {

    }
}
