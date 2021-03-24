package com.company;
import algorithms.mazeGenerators.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        IMazeGenerator mmg= new MyMazeGenerator();
        Maze maze=mmg.generate(20,20);
        for (int i = 0; i <maze.getN() ; i++) {
            System.out.println(Arrays.toString(maze.getMaze()[i]));
        }
    }
}
