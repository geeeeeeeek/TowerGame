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
    public static final float SX1 = 600;
    public static final float SX2 = 560;
    public static final float SX3 = 520;
    public static final float SX4 = 480;

    public static final float SY = 660;

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

    // 初始分数
    public static String score="0";

    private float sx;
    private float sy;

    private char num;

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

        if(score.length()==1){
            num = score.charAt(0);
            sx=SX1;sy=SY;
            drawNum(batch,num,sx,sy);
        }else if(score.length()==2){

            num = score.charAt(1);
            sx=SX1;sy=SY;
            drawNum(batch,num,sx,sy);

            num = score.charAt(0);
            sx=SX2;sy=SY;
            drawNum(batch,num,sx,sy);

        }else if(score.length()==3){
            num = score.charAt(2);
            sx=SX1;sy=SY;
            drawNum(batch,num,sx,sy);

            num = score.charAt(1);
            sx=SX2;sy=SY;
            drawNum(batch,num,sx,sy);

            num = score.charAt(0);
            sx=SX3;sy=SY;
            drawNum(batch,num,sx,sy);

        }else if(score.length()==4){

            num = score.charAt(3);
            sx=SX1;sy=SY;
            drawNum(batch,num,sx,sy);

            num = score.charAt(2);
            sx=SX2;sy=SY;
            drawNum(batch,num,sx,sy);

            num = score.charAt(1);
            sx=SX3;sy=SY;
            drawNum(batch,num,sx,sy);

            num = score.charAt(0);
            sx=SX4;sy=SY;
            drawNum(batch,num,sx,sy);

        }

    }

    // 画数字
    private void drawNum(Batch batch, char num, float sx, float sy) {
        if(num=='0'){
            batch.draw(mRegion0,sx,sy);
        }else if(num=='1'){
            batch.draw(mRegion1,sx,sy);
        }else if(num=='2'){
            batch.draw(mRegion2,sx,sy);
        }else if(num=='3'){
            batch.draw(mRegion3,sx,sy);
        }else if(num=='4'){
            batch.draw(mRegion4,sx,sy);
        }else if(num=='5'){
            batch.draw(mRegion5,sx,sy);
        }else if(num=='6'){
            batch.draw(mRegion6,sx,sy);
        }else if(num=='7'){
            batch.draw(mRegion7,sx,sy);
        }else if(num=='8'){
            batch.draw(mRegion8,sx,sy);
        }else if(num=='9'){
            batch.draw(mRegion9,sx,sy);
        }
    }
}
