package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.stage.GameStage;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class Finger extends BaseImageActor {

    public static final String TAG = "Finger";
    public static final String PICTURE = "images/finger.png";


    private Texture texture;
    private TextureRegion region;

    public Finger(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(PICTURE));

        region=new TextureRegion(texture);

        setRegion(region);

        setPosition(100,100);

    }
}
