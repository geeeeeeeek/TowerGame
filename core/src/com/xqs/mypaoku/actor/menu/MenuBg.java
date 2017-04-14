package com.xqs.mypaoku.actor.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class MenuBg extends BaseImageActor {
    public static String MENUBG_PICTURE="images/menu_bg.jpg";

    private Texture texture;
    private TextureRegion region;

    public MenuBg(MyPaokuGame mainGame) {
        super(mainGame);

        texture = new Texture(Gdx.files.internal(MENUBG_PICTURE));

        region=new TextureRegion(texture);

        setRegion(region);
    }
}
