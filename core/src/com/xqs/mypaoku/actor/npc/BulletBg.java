package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class BulletBg extends BaseImageActor implements BaseImageActor.TimerListener {
    public static final String TAG = "BulletBg";

    public static final int MODE_ONE = 1;
    public static final int MODE_TWO = 2;

    private TextureRegion bgRegion;
    private TextureRegion bulletOne;
    private TextureRegion bulletTwo;

    private TextureRegion bulletMask;

    private int mode;

    private BitmapFont bitmapFont;
    private float textWidth;
    private float textHeight;

    public BulletBg(MyPaokuGame mainGame) {
        super(mainGame);
        bgRegion = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_bg.png")));
        bulletOne = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_icon_one.png")));
        bulletTwo = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_icon_two.png")));


        // number mask
        Pixmap myPixMap = new Pixmap(80,80, Pixmap.Format.RGBA8888) ;
        myPixMap.setColor(0.2f,0.2f,0.2f,0.8f);
        myPixMap.fillCircle(40,40,40);

        bulletMask = new TextureRegion(new Texture(myPixMap));


        initFont();

        setTimerListener(this);
    }


    public void initFont(){
        bitmapFont = getMainGame().getFpsBitmapFont();
        bitmapFont.getData().setScale(0.5f);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        int ran = MathUtils.random(10,19);


        if (mode == MODE_ONE) {
            batch.draw(bgRegion, 30, 400);
            batch.draw(bulletOne, 30, 400);
        } else if (mode == MODE_TWO) {
            batch.draw(bgRegion, 30, 500);
            batch.draw(bulletTwo, 30, 500);

            batch.draw(bulletMask,40,510);

            GlyphLayout layout = new GlyphLayout();
            layout.setText(bitmapFont,""+ran);
            textWidth = layout.width;
            textHeight = layout.height;
            bitmapFont.setColor(Color.WHITE);
            bitmapFont.getData().setScale(0.5f);
            bitmapFont.draw(batch,""+ran,30+(100/2-textWidth/2),500+(100/2+textHeight/2));

        }
    }

    @Override
    public void orderAct(float delta, int counter) {

    }
}
