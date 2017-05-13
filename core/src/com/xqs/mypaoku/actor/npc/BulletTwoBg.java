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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.SoundHelper;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.app.Prefs;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.stage.GameStage;
import com.xqs.mypaoku.util.GameState;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class BulletTwoBg extends BaseImageActor {
    public static final String TAG = "BulletBg";


    private TextureRegion bgRegion;
    private TextureRegion bulletTwo;

    private TextureRegion bulletMask;

    public static int mode;

    private BitmapFont bitmapFont;
    private float textWidth;
    private float textHeight;

    private GlyphLayout layout = new GlyphLayout();

    public BulletTwoBg(MyPaokuGame mainGame) {
        super(mainGame);
        bgRegion = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_bg.png")));
        bulletTwo = new TextureRegion(new Texture(Gdx.files.internal("images/bullet_icon_two.png")));


        // number mask
        Pixmap myPixMap = new Pixmap(80, 80, Pixmap.Format.RGBA8888);
        Pixmap.setBlending(Pixmap.Blending.None);
        myPixMap.setColor(0.2f, 0.2f, 0.2f, 0.6f);
        myPixMap.fillCircle(40, 40, 40);

        bulletMask = new TextureRegion(new Texture(myPixMap));

        setRegion(bgRegion);
        setPosition(30, 500);

        initFont();

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(GameStage.getGameState() == GameState.GAMING) {
                    mode = 1;
                    BulletOneBg.mode = 0;
                    SoundHelper.playSwitchGunSound();
                }
            }
        });


    }


    public void initFont() {
        bitmapFont = getMainGame().getFpsBitmapFont();
        bitmapFont.getData().setScale(0.5f);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        int leftBulletNumber = Prefs.getPrefs().getPlayerBulletTwoLeftNumber();

        batch.draw(bgRegion, 30, 500);
        batch.draw(bulletTwo, 30, 500);

        if(mode == 1) {
            batch.draw(bulletMask, 40, 510);

            layout.setText(bitmapFont, "" + leftBulletNumber);
            textWidth = layout.width;
            textHeight = layout.height;
            bitmapFont.setColor(Color.valueOf(Res.Color.FONT_MAIN));
            bitmapFont.getData().setScale(0.5f);
            bitmapFont.draw(batch, "" + leftBulletNumber, 30 + (100 / 2 - textWidth / 2), 500 + (100 / 2 + textHeight / 2));
        }
    }

}