package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class BulletBg extends BaseImageActor {
    public static final int MODE_ONE = 1;
    public static final int MODE_TWO = 2;

    private TextureRegion bgRegion;
    private TextureRegion bulletOne;
    private TextureRegion bulletTwo;

    private int mode;

    public BulletBg(MyPaokuGame mainGame) {
        super(mainGame);
        bgRegion = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_bg.png")));
        bulletOne = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_icon_one.png")));
        bulletTwo = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_icon_two.png")));
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (mode == MODE_ONE) {
            batch.draw(bgRegion, 30, 400);
            batch.draw(bulletOne, 50, 420);
        } else if (mode == MODE_TWO) {
            batch.draw(bgRegion, 30, 500);
            batch.draw(bulletTwo, 50, 520);
        }
    }
}
