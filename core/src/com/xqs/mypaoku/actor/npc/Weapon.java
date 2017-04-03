package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.res.Res;

/**
 * Created by Administrator on 2017/4/2 0002.
 */

public class Weapon extends BaseImageActor {

    private Texture pixmaptex;
    private TextureRegion bgRegion;
    private TextureRegion bulletRegion;

    public Weapon(MyPaokuGame mainGame) {
        super(mainGame);

        Pixmap pixmap = new Pixmap(100, 60, Pixmap.Format.RGBA8888 );
        pixmap.setColor(0,0,0,0.2f);
        pixmap.fillRectangle(0,0,100,60);
        pixmaptex = new Texture( pixmap );
        pixmap.dispose();

        bgRegion=new TextureRegion(pixmaptex);


        bulletRegion=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_BULLET_ONE_FLY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(bulletRegion,20,getMainGame().getWorldHeight()-140);

        batch.draw(bgRegion,20,getMainGame().getWorldHeight()-140);
    }
}
