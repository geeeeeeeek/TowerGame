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


    public PlaneEnemy(MyPaokuGame mainGame) {
        super(mainGame);

        this.mainGame=mainGame;

        position.x=this.mainGame.getWorldWidth();
        position.y=500;

        setStopX(220);

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
    public Animation getHurtAnimation() {
        TextureAtlas.AtlasRegion deadRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_ENEMY_PLANE_DEAD);
        Animation hurtAnimation = new  Animation(0.1F, TextureUtil.getTextureRegions(deadRegion,1,6));
        return hurtAnimation;
    }


    @Override
    public void act(float delta) {
        if(GameStage.gameState== GameState.pause){
            return;
        }
        super.act(delta);
    }
}
