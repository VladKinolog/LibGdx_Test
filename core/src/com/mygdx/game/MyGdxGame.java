package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Wall.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i=0; i<20; i++) {
			for (int y=0; y<20; y++) {

				batch.draw(img, i * 33, y * 33, 33, 33);
			}
		}

		batch.end();
	}
}
