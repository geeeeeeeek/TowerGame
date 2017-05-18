package com.xqs.mypaoku.actor.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class QuitButton extends Button {
    public static final String TAG = "QuitButton";

    public static final String BTN_UP="images/btn_quit_default.png";
    public static final String BTN_DOWN="images/btn_quit_default.png";

    public static final int MARGIN = 380;

    private Texture upTexture;
    private Texture downTexture;

    private MyPaokuGame mainGame;

    private BitmapFont bitmapFont;
    private float textWidth;
    private float textHeight;

    public QuitButton(MyPaokuGame mainGame) {
        this.mainGame = mainGame;

        upTexture = new Texture(Gdx.files.internal(BTN_UP));
        downTexture = new Texture(Gdx.files.internal(BTN_DOWN));

        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));
        style.checked = new TextureRegionDrawable(new TextureRegion(downTexture));

        setStyle(style);

        setWidth(upTexture.getWidth());
        setHeight(upTexture.getHeight());

        float px = mainGame.getWorldWidth()/2 - upTexture.getWidth()/2;
        float py = mainGame.getWorldHeight() - upTexture.getHeight() - MARGIN;

        setPosition(px,py);

        initFont();
    }

    public void initFont(){
        bitmapFont = this.mainGame.getFpsBitmapFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        GlyphLayout layout = new GlyphLayout();
//        layout.setText(bitmapFont,Res.text_quit);
//        textWidth = layout.width;
//        textHeight = layout.height;
//        bitmapFont.draw(batch,Res.text_quit,this.getX()+(getWidth()/2-textWidth/2),this.getY()+this.getHeight()-(getHeight()/2-textHeight/2));
//        bitmapFont.setColor(Color.valueOf(Res.Color.FONT_MAIN));
//        bitmapFont.getData().setScale(0.4f);
    }
}
