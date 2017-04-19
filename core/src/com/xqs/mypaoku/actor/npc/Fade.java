package com.xqs.mypaoku.actor.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.xqs.mypaoku.MyPaokuGame;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class Fade extends Actor {
    private ShapeRenderer fadeRenderer;

    private MyPaokuGame game;

    public Fade(MyPaokuGame game){
        this.game = game;
        fadeRenderer = new ShapeRenderer(8);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        fadeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        fadeRenderer.setColor(new Color(0, 0, 0, 0.5f));
        fadeRenderer.rect(0, 0, game.getWorldWidth(),game.getWorldHeight());
        fadeRenderer.end();
        batch.begin();

    }

}
