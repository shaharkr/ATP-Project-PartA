package com.company;
import algorithms.mazeGenerators.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        AMazeGenerator mmg= new MyMazeGenerator();
        Maze maze=mmg.generate(100,100);
        //System.out.println(Arrays.deepToString(maze.getMaze()));


    }
}
