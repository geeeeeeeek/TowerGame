package com.xqs.mypaoku.actor.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/16 0016.
 */

public class BackButton extends BaseImageActor {


    private Texture texture;
    private TextureRegion region;
    public BackButton(MyPaokuGame mainGame) {
        super(mainGame);
        texture = new Texture(Gdx.files.internal("images/btn_back.png"));

        region=new TextureRegion(texture);

        setRegion(region);

        setPosition(50,50);
    }
}
