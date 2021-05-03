package test;
import IO.*;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        try{
            String mazeFileName = "savedMaze.maze";
            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(14, 13); //Generate new maze
            //maze.print();
            try {
                // save maze to a file
                OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
                out.write(maze.toByteArray());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte savedMazeBytes[] = new byte[0];
            try {
                //read maze from file
                InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
                savedMazeBytes = new byte[maze.toByteArray().length];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("-------------------");
            Maze loadedMaze = new Maze(savedMazeBytes);
            //loadedMaze.print();
            boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
            System.out.println(String.format("Mazes equal: %s",areMazesEquals)); //maze should be equal to loadedMaze

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
