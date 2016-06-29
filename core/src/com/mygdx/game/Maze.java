package com.mygdx.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Генерация лаберинта
 */
public class Maze {

        public static final int CELL = 0;
        public static final int WALL = 1;
        public static final int VISIT = 2;
        long counter = 0;
        private int labyrinthWeigh;
        private int labyrinthHeight;
        private int [][] labyrinthField = null;
        int unvisitedCount;
        Cell initCell = new Cell(2,2);
        Stack<Cell> stackCell = new Stack<Cell>();

        class Cell{
            int x;
            int y;

            Cell (int x,int y){
                this.x = x;
                this.y = y;
            }
        }


        // Конструкторы + генерация начального поля
        Maze(){
            this.labyrinthWeigh = 15;
            this.labyrinthHeight = 15;
            labyrinthField = this.fieldGenerator();

        }

        Maze(int length){
            if (length % 2 == 0){
                this.labyrinthWeigh = length + 1;
                this.labyrinthHeight = length + 1;
            } else {
                this.labyrinthWeigh = length;
                this.labyrinthHeight = length;
            }
            labyrinthField = this.fieldGenerator();
        }

        Maze(int length,int height){
            if (length % 2 == 0){
                this.labyrinthWeigh = length + 1;
            } else {
                this.labyrinthWeigh = length;
            }
            if (height % 2 == 0){
                this.labyrinthHeight = height + 1;
            }
            else {
                this.labyrinthHeight = height;
            }
            labyrinthField = this.fieldGenerator();
        }

        public int [][] getMazeArray () {
            return labyrinthField;
        }

        // Инициализация полей лаберинта
        private  int [][] fieldGenerator (){

            int [][] labyrinthField = new int[labyrinthWeigh][labyrinthHeight];
            for (int i=0; i<labyrinthWeigh; i++){
                for (int j=0; j<labyrinthHeight; j++){
                    if ((i % 2 != 0 && j % 2 != 0) && (i < labyrinthWeigh - 1 && j < labyrinthHeight - 1)) {
                        labyrinthField [i][j] = CELL;
                    }
                    else {
                        labyrinthField [i][j] = WALL;
                    }
                }
            }

            return labyrinthField;
        }

//        public static void main(String[] args) throws IOException {
//            Maze lab = new Maze(1000,1000);
//            lab.generatorMaze ();
//
//
////            FileWriter filewriter = new FileWriter(new File("TestMaze.txt"));
//
//            for (int[] i:lab.labyrinthField){
//                for (int j:i) {
//                    if (j == VISIT) {
//                        //System.out.print("O ");
//                        filewriter.write("О ");
//                    } else {
//                        filewriter.write("X ");
//                        //System.out.print("X ");
//                    }
//
//                }
//                filewriter.write("\n");
//
//
//                //System.out.println();
//            }
//            filewriter.flush();
//            filewriter.close();
//        }

        // массив непосещеных соседей
        private List<Cell> getNeighbours (Cell c, int distance){
            int x = c.x;
            int y = c.y;
            Cell up = new Cell(x,y+distance);
            Cell dwn = new Cell(x,y-distance);
            Cell lft = new Cell(x+distance,y);
            Cell rht = new Cell(x-distance,y);
            Cell [] checkDir = {up,dwn,lft,rht};
            List<Cell> notVisNeighbor= new ArrayList<Cell>();


            for (Cell i:checkDir){
                if (i.x > 0 && i.y > 0 && i.x < labyrinthWeigh && i.y < labyrinthHeight){
                    Cell currentCell = new Cell(i.x,i.y);
                    if (labyrinthField[i.x][i.y] != VISIT && labyrinthField[i.x][i.y] != WALL){
                        notVisNeighbor.add(currentCell);
                    }
                }
            }
            return notVisNeighbor;
        }

        // Удаление стенки лаберинта

        private void dellWall(Cell first, Cell second){
            int dx = second.x - first.x;
            int dy = second.y - first.y;
            int addX,addY;
            int targetX,targetY;


            addX = (dx != 0) ? dx/Math.abs(dx):0;
            addY = (dy != 0) ? dy/Math.abs(dy):0;

            targetX = first.x + addX;
            targetY = first.y + addY;

            Cell currentWall = new Cell(targetX,targetY);

            labyrinthField[currentWall.x][currentWall.y] = VISIT;

        }

        public List<Cell> getUnvisited (){
            //Cell unvisitedCell;
            List <Cell> unvisited = new ArrayList<Cell>();
            for (int i = 0; i>labyrinthField.length; i++){
                for (int j = 0; j>labyrinthField.length; j++){
                    if (labyrinthField[i][j] == CELL){
                        unvisited.add(new Cell(i,j));

                    }
                }
            }
            return unvisited;
        }

        // Генерация матрицы Лаберинта
        public void generatorMaze (){

            Cell currentCell = new Cell(1,1);

            Cell targetCell;
            List<Cell> neighbours;
            List<Cell>  unvisited;

            do {
                counter++;
                System.out.println(counter);
                labyrinthField[currentCell.x][currentCell.y] = VISIT;
                neighbours = getNeighbours(currentCell, 2);
                if (neighbours.size() != 0) {
                    Collections.shuffle(neighbours);
                    targetCell = neighbours.get(0);
                    stackCell.push(currentCell);
                    dellWall(currentCell,targetCell);
                    currentCell = targetCell;
                }

                else if (stackCell.size() > 0){
                    currentCell = stackCell.pop();
                }

                else {


                    unvisited = getUnvisited ();
                    Collections.shuffle(unvisited);
                    if (unvisited.size()> 0) {
                        currentCell = unvisited.get(0);
                    }


                }


                //рассмотреть возможность упростить циклы определения не посещенных клеток
                unvisitedCount = 0;
                for (int i []:labyrinthField) {
                    for (int j:i) {
                        if (j == CELL) unvisitedCount++;

                    }
                }

            }while (unvisitedCount>0);

        }


}
