package com.xqs.mypaoku.actor;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;

/**
 * Created by Administrator on 2017/4/22 0022.
 */

public class SoundHelper {



    public static Sound getSound(MyPaokuGame game,String name){
        return game.getAssetManager().get(name, Sound.class);
    }

    public static Music getMusic(MyPaokuGame game,String name){
        return game.getAssetManager().get(name,Music.class);
    }
}
