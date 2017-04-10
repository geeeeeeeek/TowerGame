package com.xqs.mypaoku.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class Prefs {

    public static final String PREFS_NAME = "my prefs";

    public static final String SOUND = "sound";

    public static Prefs get(){
        return new Prefs();
    }

    private static Preferences getInstance(){
        Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);
        return prefs;
    }

    // ------ sound state ------
    public void setSoundState(boolean state){
        getInstance().putBoolean(SOUND,state);
    }

    public boolean getSoundState(){
        return getInstance().getBoolean(SOUND,true);
    }
}
