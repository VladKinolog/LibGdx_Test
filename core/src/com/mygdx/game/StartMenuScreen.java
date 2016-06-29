package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Экран главного меню
 */
public class StartMenuScreen implements Screen{

    final MazeGame mazeGame;
    OrthographicCamera camera;

    public StartMenuScreen (final MazeGame game){
        mazeGame = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,2.0f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        mazeGame.batch.begin();
        mazeGame.font.draw(mazeGame.batch, "Welcome to Drop!!! ", 100, 150);
        mazeGame.font.draw(mazeGame.batch, "Tap anywhere to begin!", 100, 100);

        mazeGame.batch.end();

        if (Gdx.input.isTouched()){
            mazeGame.setScreen(new MazeGameScreen(mazeGame));
            dispose();
        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
