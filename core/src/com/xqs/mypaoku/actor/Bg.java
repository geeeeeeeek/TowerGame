package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/1/15 0015.
 */

public class Bg extends BaseImageActor {

    private Texture texture;
    private TextureRegion region;

    private float moveVelocity=20f;

    private float offX;

    private int x=0;

    public Bg(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal("bg.png"));

        region=new TextureRegion(texture);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        offX+=delta*moveVelocity;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(region,0,0);
    }

}
