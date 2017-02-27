package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/2/26 0026.
 */

public class Tower extends BaseImageActor {
    public static String towerPicName="tower01.png";

    private Texture texture;
    private TextureRegion region;

    public Tower(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(towerPicName));

        region=new TextureRegion(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(region,50,100);
    }
}
