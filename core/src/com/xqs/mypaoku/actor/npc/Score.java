package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.xqs.mypaoku.MyPaokuGame;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class Score extends Actor {
    public static final float SX = 600;
    public static final float SY = 620;
    private MyPaokuGame game;

    private TextureRegion mRegion0;
    private TextureRegion mRegion1;
    private TextureRegion mRegion2;
    private TextureRegion mRegion3;
    private TextureRegion mRegion4;
    private TextureRegion mRegion5;
    private TextureRegion mRegion6;
    private TextureRegion mRegion7;
    private TextureRegion mRegion8;
    private TextureRegion mRegion9;

//    int score=12;
    String score="12";

    public Score(MyPaokuGame game){
        this.game = game;
        mRegion0 = new TextureRegion(new Texture(Gdx.files.internal("images/num_zero.png")));
        mRegion1 = new TextureRegion(new Texture(Gdx.files.internal("images/num_one.png")));
        mRegion2 = new TextureRegion(new Texture(Gdx.files.internal("images/num_two.png")));
        mRegion3 = new TextureRegion(new Texture(Gdx.files.internal("images/num_three.png")));
        mRegion4 = new TextureRegion(new Texture(Gdx.files.internal("images/num_four.png")));
        mRegion5 = new TextureRegion(new Texture(Gdx.files.internal("images/num_five.png")));
        mRegion6 = new TextureRegion(new Texture(Gdx.files.internal("images/num_six.png")));
        mRegion7 = new TextureRegion(new Texture(Gdx.files.internal("images/num_seven.png")));
        mRegion8 = new TextureRegion(new Texture(Gdx.files.internal("images/num_eight.png")));
        mRegion9 = new TextureRegion(new Texture(Gdx.files.internal("images/num_nine.png")));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if(score.length()>1){
            batch.draw(mRegion1,100,100,10,10,30,30,1,1,0);
        }

    }
}
