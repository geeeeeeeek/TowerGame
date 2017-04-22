package com.xqs.mypaoku.actor.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/22 0022.
 */

public class FacebookButton extends BaseImageActor {
    public static final String TAG = "FacebookButton";

    public static final String BTN_FACEBOOK="images/icon_facebook.png";

    public static final int MARGIN = 10;

    private Texture onTexture;

    private TextureRegion onRegion;


    public FacebookButton(final MyPaokuGame game){
        super(game);

        onTexture = new Texture(Gdx.files.internal(BTN_FACEBOOK));

        onRegion=new TextureRegion(onTexture);

        setRegion(onRegion);

        setPosition(100,100);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.shareApp();
            }
        });
    }
}
