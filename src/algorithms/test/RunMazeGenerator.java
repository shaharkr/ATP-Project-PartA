package algorithms.test;
import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.*;
public class RunMazeGenerator {
    public static void main(String[] args) {
        //testMazeGenerator(new EmptyMazeGenerator());
        //testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator) {
        // prints the time it takes the algorithm to run
        try{System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(3,5/*rows*/,5/*columns*/)));
        // generate another maze
        Maze3D maze = mazeGenerator.generate(3,5/*rows*/, 5/*columns*/);
        // prints the maze
        maze.print();
        // get the maze entrance
        Position3D startPosition = maze.getStartPosition3D();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition3D()));}
        catch (Exception e){System.out.println(e.getMessage());}

    }
}