package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

class MazeGameScreen implements Screen {


    final MazeGame mazeGame;

    Rectangle ball;
    Rectangle wall;
    Rectangle mazeOut;
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture wallImg;
    Texture ballImg;
	Maze maze;
	int mazeWidth = 48;
	int mazeHeight = 28;
    int xBall = 16;
    int yBall = 16;
    boolean accessDraw = false;

    // Временные переменные
    long deltaTime = 1000;
    long currentTime = System.currentTimeMillis();
    long futureTime;


    public MazeGameScreen (final MazeGame game){
        this.mazeGame = game;

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
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();

        futureTime = System.currentTimeMillis();


		for (int i=0; i< maze.getMazeArray().length; i++) {
			for (int y=0; y< maze.getMazeArray()[i].length; y++) {

                if (maze.getMazeArray()[i][y] == Maze.VISIT_WALL) {

                    batch.draw(wallImg, i * 16, y * 16);
                }
			}
		}
//        if (accessDraw){
//            batch.draw(wallImg,xBall,yBall);
//        }
        batch.draw(ballImg,xBall,yBall);
		batch.end();



//          Обработка нажатия клавиш и столкновение


        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){

            // Проверка условия является ли ячейка стеной или посещенной стеной.
            // Если да то стоим на месте и меняем значение стены на посещенную,
            // если нет перемещаемя в данную точку.
            if (maze.getMazeArray()[(xBall - 16)/16][yBall/16]== Maze.WALL ||
                    maze.getMazeArray()[(xBall - 16)/16][yBall/16]== Maze.VISIT_WALL){
                maze.getMazeArray()[(xBall - 16)/16][yBall/16]= Maze.VISIT_WALL;
                return;}
            xBall -= 16;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if (maze.getMazeArray()[(xBall + 16)/16][yBall/16]== Maze.WALL ||
                    maze.getMazeArray()[(xBall + 16)/16][yBall/16]== Maze.VISIT_WALL){
                maze.getMazeArray()[(xBall + 16)/16][yBall/16]= Maze.VISIT_WALL;
                return;}
            xBall += 16;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if (maze.getMazeArray()[xBall/16][(yBall+16)/16]== Maze.WALL ||
                    maze.getMazeArray()[xBall/16][(yBall+16)/16] == Maze.VISIT_WALL){
                maze.getMazeArray()[xBall/16][(yBall+16)/16] = Maze.VISIT_WALL;
            return;}
            yBall += 16;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if (maze.getMazeArray()[xBall/16][(yBall-16)/16]== Maze.WALL ||
                    maze.getMazeArray()[xBall/16][(yBall-16)/16] == Maze.VISIT_WALL){
                maze.getMazeArray()[xBall/16][(yBall-16)/16]= Maze.VISIT_WALL;
                return;}
            yBall -= 16;
        }
//        Проверка выхода за пределы экрана
        if(xBall <  16) xBall = 16;
        if(xBall > maze.getMazeArray().length * 16 - 32) xBall = maze.getMazeArray().length * 16 - 32;
        if(yBall < 16) yBall = 16;
        if(yBall > maze.getMazeArray().length * 16 - 32) yBall = maze.getMazeArray().length * 16 - 32;

        futureTime = System.currentTimeMillis() - futureTime;
        System.out.println(futureTime);
	}

    @Override
    public void dispose() {
        wallImg.dispose();
        ballImg.dispose();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }
}
