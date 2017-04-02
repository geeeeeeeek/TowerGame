package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/2 0002.
 */

public class Weapon extends BaseImageActor {
    Texture pixmaptex;
    public Weapon(MyPaokuGame mainGame) {
        super(mainGame);

        Pixmap pixmap = new Pixmap(100, 60, Pixmap.Format.RGBA8888 );
        pixmap.setColor(0,0,0,0.2f);
        pixmap.fillRectangle(0,0,100,60);
        pixmaptex = new Texture( pixmap );
        pixmap.dispose();

        TextureRegion region=new TextureRegion(pixmaptex);
        setRegion(region);
        setPosition(20,mainGame.getWorldHeight()-150);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
