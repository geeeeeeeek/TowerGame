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
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class StartButton extends Button {
    public static final String TAG = "StartButton";

    public static final String BTN_UP="images/btn_start_default.png";
    public static final String BTN_DOWN="images/btn_start_pressed.png";

    public static final int MARGIN = 100;

    private Texture upTexture;
    private Texture downTexture;

    private MyPaokuGame mainGame;

    private BitmapFont bitmapFont;
    private float textWidth;
    private float textHeight;

    public StartButton(MyPaokuGame mainGame) {
        this.mainGame = mainGame;

        upTexture = new Texture(Gdx.files.internal(BTN_UP));
        downTexture = new Texture(Gdx.files.internal(BTN_DOWN));

        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));

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
        bitmapFont.getData().setScale(0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


        GlyphLayout layout = new GlyphLayout();
        layout.setText(bitmapFont,"开始游戏");
        textWidth = layout.width;
        textHeight = layout.height;
        bitmapFont.draw(batch,"开始游戏",this.getX()+(getWidth()/2-textWidth/2),this.getY()+this.getHeight()-(getHeight()/2-textHeight/2));
        bitmapFont.setColor(Color.RED);
        bitmapFont.getData().setScale(0.5f);
    }


}
