package com.xqs.mypaoku.actor.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/16 0016.
 */

public class LevelIcon extends BaseImageActor {
    public static final String TAG = "LevelIcon";

    public static final String BTN_LOCK_ON="images/btn_lock_on.png";
    public static final String BTN_LOCK_OFF="images/btn_lock_off.png";

    private Texture onTexture;
    private Texture offTexture;

    private TextureRegion onRegion;
    private TextureRegion offRegion;

    public LevelIcon(MyPaokuGame mainGame) {
        super(mainGame);

        onTexture = new Texture(Gdx.files.internal(BTN_LOCK_ON));
        offTexture = new Texture(Gdx.files.internal(BTN_LOCK_OFF));

        onRegion=new TextureRegion(onTexture);
        offRegion=new TextureRegion(offTexture);

        setRegion(onRegion);

        setScale(0.6f,0.6f);

    }
}
