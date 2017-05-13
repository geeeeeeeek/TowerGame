package com.xqs.mypaoku.actor;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.app.Prefs;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;

/**
 * Created by Administrator on 2017/4/22 0022.
 */

public class SoundHelper {

    public static Sound passSound;
    public static Sound gameOverSound;
    public static Sound scoreSound;
    public static Sound buttonSound;
    public static Sound playerFireSound;
    public static Sound explodeSound;
    public static Sound coinSound;
    public static Sound switchGunSound;

    public static Music bgMusic;

    public static void initSound(MyPaokuGame game){
        passSound = getSound(game, Res.Audios.AUDIO_PASS);
        gameOverSound = getSound(game, Res.Audios.AUDIO_GAMEOVER);
        scoreSound = getSound(game, Res.Audios.AUDIO_SCORE);
        buttonSound = getSound(game, Res.Audios.AUDIO_BUTTON);
        playerFireSound = getSound(game,Res.Audios.AUDIO_PLAYER_FIRE);
        explodeSound = getSound(game,Res.Audios.AUDIO_EXPLODE);
        coinSound = getSound(game,Res.Audios.AUDIO_GET_COIN);
        switchGunSound = getSound(game,Res.Audios.AUDIO_SWITCH_GUN);

        bgMusic = getMusic(game,Res.Audios.AUDIO_BG);

        stopAllSound();
    }

    public static Sound getSound(MyPaokuGame game,String name){
        return game.getAssetManager().get(name, Sound.class);
    }

    public static Music getMusic(MyPaokuGame game,String name){
        return game.getAssetManager().get(name,Music.class);
    }

    public static void playBgMusic(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            bgMusic.setLooping(true);
            bgMusic.setVolume(0.6f);
            bgMusic.play();
        }
    }

    public static void pauseBgMusic(){
        bgMusic.pause();
    }

    public static void stopAllSound(){
        passSound.stop();
        gameOverSound.stop();
        scoreSound.stop();
        bgMusic.stop();
    }

    public static void playPass(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            passSound.play();
        }
    }

    public static void playGameOver(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            gameOverSound.play();
        }
    }

    public static void playScore(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            scoreSound.play();
        }
    }

    public static void playButtonSound(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            buttonSound.play();
        }
    }

    public static void playFireSound(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            playerFireSound.play();
        }
    }

    public static void playExplodeSound(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            explodeSound.play();
        }
    }

    public static void playGetCoinSound(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            coinSound.play();
        }
    }

    public static void playSwitchGunSound(){
        boolean canSound = Prefs.getPrefs().isSoundEffectsEnabled();
        if(canSound){
            switchGunSound.play();
        }
    }

}
