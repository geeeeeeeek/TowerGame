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
import com.xqs.mypaoku.res.Res;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class HelpPopup extends BaseImageActor implements BaseImageActor.TimerListener {
    private TextureRegion bg;

    private int counter;
    private int visibleTime;


    private BitmapFont bitmapFont;
    private float textWidth;
    private float textHeight;
    private GlyphLayout layout = new GlyphLayout();

    public HelpPopup(MyPaokuGame mainGame) {
        super(mainGame);

        Pixmap myPixMap = new Pixmap((int)mainGame.getWorldWidth(),(int)mainGame.getWorldHeight(), Pixmap.Format.RGBA8888);
        myPixMap.setColor(0.2f, 0.2f, 0.2f, 0.4f);
        myPixMap.fillRectangle(0,0, (int)mainGame.getWorldWidth(),(int)mainGame.getWorldHeight());

        bg = new TextureRegion(new Texture(myPixMap));

        setTimerListener(this);

        initFont();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

//        batch.draw(bg, 300,200,getMainGame().getWorldWidth()-300*2,getMainGame().getWorldHeight()-200*2);

        layout.setText(bitmapFont, Res.text_help_content);
        textWidth = layout.width;
        textHeight = layout.height;
        bitmapFont.setColor(Color.valueOf(Res.Color.FONT_LEVEL));
        bitmapFont.getData().setScale(0.5f);
        bitmapFont.draw(batch, Res.text_help_content, 200, 680);
    }

    public void initFont() {
        bitmapFont = getMainGame().getFpsBitmapFont();
        bitmapFont.getData().setScale(0.5f);
    }


    @Override
    public void orderAct(float delta, int counter) {

    }
}
