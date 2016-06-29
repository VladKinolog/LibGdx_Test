package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
    Rectangle ball;
    Rectangle wall;
    Rectangle mazeOut;
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture wallImg;
    Texture ballImg;
	Maze maze;
	int mazeWidth = 28;
	int mazeHeight = 28;
    int xBall = 16;
    int yBall = 16;
    boolean accessDraw = false;

	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
		wallImg = new Texture("Wall.png");
        ballImg = new Texture("ball.png");
		maze = new Maze(mazeWidth, mazeHeight);
		maze.generatorMaze();
        wall = new Rectangle();
        wall.x = 0;
        wall.y = 0;


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int i=0; i< maze.getMazeArray().length; i++) {
			for (int y=0; y< maze.getMazeArray().length; y++) {
				if (maze.getMazeArray()[i][y] == Maze.WALL)
				batch.draw(wallImg, i * 16, y * 16);
			}
		}
        if (accessDraw){
            batch.draw(wallImg,xBall,yBall);
        }
        batch.draw(ballImg,xBall,yBall);
		batch.end();

//          Обработка нажатия клавиш и столкновение


        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if (maze.getMazeArray()[(xBall - 16)/16][yBall/16]== Maze.WALL){
                accessDraw = true;
                return;}
            xBall -= 16;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if (maze.getMazeArray()[(xBall + 16)/16][yBall/16]== Maze.WALL){
                accessDraw = true;
                return;}
            xBall += 16;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if (maze.getMazeArray()[xBall/16][(yBall+16)/16]== Maze.WALL){
                accessDraw = true;
                return;}
            yBall += 16;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if (maze.getMazeArray()[xBall/16][(yBall-16)/16]== Maze.WALL){
                accessDraw = true;
                return;}
            yBall -= 16;
        }
//        Проверка выхода за пределы экрана
        if(xBall <  16) xBall = 16;
        if(xBall > maze.getMazeArray().length * 16 - 32) xBall = maze.getMazeArray().length * 16 - 32;
        if(yBall < 16) yBall = 16;
        if(yBall > maze.getMazeArray().length * 16 - 32) yBall = maze.getMazeArray().length * 16 - 32;
	}

    @Override
    public void dispose() {
        wallImg.dispose();
        ballImg.dispose();
    }
}
