package test;
import algorithms.maze3D.*;
public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMaze3DGenerator mazeGenerator) {
        try{// prints the time it takes the algorithm to run
            System.out.println(String.format("Maze generationtime(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100,100/*rows*/,100/*columns*/)));
            // generate another maze
            Maze3D maze = mazeGenerator.generate(100,100/*rows*/, 100/*columns*/);
            // prints the maze
            maze.print();
            // get the maze entrance
            Position3D startPosition = maze.getStartPosition3D();
            // print the start position
            System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
            // prints the maze exit position
            System.out.println(String.format("Goal Position: %s", maze.getGoalPosition3D()));}
        catch (Exception e){ System.out.println(e.getMessage());}
    }
}
