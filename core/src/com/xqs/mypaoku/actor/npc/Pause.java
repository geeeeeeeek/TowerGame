package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xqs.mypaoku.MyPaokuGame;
import com.xqs.mypaoku.actor.base.BaseImageActor;
import com.xqs.mypaoku.res.Res;
import com.xqs.mypaoku.util.Util;

/**
 * Created by Administrator on 2017/4/2 0002.
 */

public class Pause extends BaseImageActor {
    public static final String TAG = "Pause";

    private TextureAtlas.AtlasRegion region;
    public Pause(MyPaokuGame mainGame) {
        super(mainGame);

        region=mainGame.getAtlas().findRegion(Res.Atlas.IMAGE_NPC_BTN_PAUSE);

        setRegion(region);

        setPosition(mainGame.getWorldWidth()-getWidth(),mainGame.getWorldHeight()-getHeight());

    }
}
