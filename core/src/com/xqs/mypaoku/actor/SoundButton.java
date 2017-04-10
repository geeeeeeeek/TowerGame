package com.xqs.mypaoku.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.app.Prefs;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class SoundButton extends BaseImageActor {

    public static final String BTN_SOUND_ON="btn_sound_on.png";
    public static final String BTN_SOUND_OFF="btn_sound_off.png";

    public static final int MARGIN = 10;

    private Texture onTexture;
    private Texture offTexture;

    private TextureRegion onRegion;
    private TextureRegion offRegion;


    public SoundButton(MyPaokuGame game){
        super(game);

        onTexture = new Texture(Gdx.files.internal(BTN_SOUND_ON));
        offTexture = new Texture(Gdx.files.internal(BTN_SOUND_OFF));

        onRegion=new TextureRegion(onTexture);
        offRegion=new TextureRegion(offTexture);

        setCurrentRegion();

        setPosition(MARGIN,getMainGame().getWorldHeight()-getHeight()-MARGIN);
    }

    public void setCurrentRegion(){
        boolean pressed = Prefs.get().getSoundState();
        if(pressed){
            setRegion(onRegion);
        }else {
            setRegion(offRegion);
        }
    }

    public void press() {
        boolean pressed = Prefs.get().getSoundState();
        if(pressed){
            setRegion(offRegion);
        }else {
            setRegion(onRegion);
        }
        Prefs.get().setSoundState(!pressed);
    }
}
