package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/5/6 0006.
 */

public class NoBulletPopup extends BaseImageActor implements BaseImageActor.TimerListener {
    private TextureRegion bg;

    private int counter;
    private int visibleTime;


    private BitmapFont bitmapFont;
    private float textWidth;
    private float textHeight;
    private GlyphLayout layout = new GlyphLayout();

    public NoBulletPopup(MyPaokuGame mainGame) {
        super(mainGame);

        Pixmap myPixMap = new Pixmap((int)mainGame.getWorldWidth(),(int)mainGame.getWorldHeight(), Pixmap.Format.RGBA8888);
        myPixMap.setColor(0.2f, 0.2f, 0.2f, 0.4f);
        myPixMap.fillRectangle(0,0, (int)mainGame.getWorldWidth(),(int)mainGame.getWorldHeight());

        bg = new TextureRegion(new Texture(myPixMap));

        setTimerListener(this);

        initFont();

        setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(bg, 0,0);

        layout.setText(bitmapFont, "No Bullets");
        textWidth = layout.width;
        textHeight = layout.height;
        bitmapFont.setColor(Color.valueOf("#97e021"));
        bitmapFont.getData().setScale(0.5f);
        bitmapFont.draw(batch, "No Bullets", getMainGame().getWorldWidth()/2-textWidth/2,getMainGame().getWorldHeight()/2+textHeight);
    }

    public void initFont() {
        bitmapFont = getMainGame().getFpsBitmapFont();
        bitmapFont.getData().setScale(0.5f);
    }

    public void setNoBulletPopupVisible(){
        setVisible(true);
        visibleTime = counter;
    }

    @Override
    public void orderAct(float delta, int counter) {
        this.counter = counter;

        // 显示0.5秒
        if(counter-visibleTime>5){
            setVisible(false);
        }
    }
}
