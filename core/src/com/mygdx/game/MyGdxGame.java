package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture img;
	Maze maze;
	int mazeWidth = 28;
	int mazeHeight = 28;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		img = new Texture("Wall.png");
		maze = new Maze(mazeWidth,mazeHeight);
		maze.generatorMaze();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i=0; i<=mazeWidth; i++) {
			for (int y=0; y<=mazeHeight; y++) {
				if (maze.getMazeArray()[i][y] == Maze.WALL)
				batch.draw(img, i * 16, y * 16);
			}
		}

		batch.end();
	}
}
