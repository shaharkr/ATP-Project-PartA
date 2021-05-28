package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.byteIntOperations;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;

import static IO.byteIntOperations.byteToInt;

/**
 * Server strategy for solving maze's.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private String pathToDir;

    /**
     * creates temp dir, to save solutions there.
     */
    public ServerStrategySolveSearchProblem() {
        pathToDir = System.getProperty("java.io.tmpdir");
    }

    /**
     * @param inFromClient input stream of a maze, written as an object.
     * @param outToClient an output stream, we will write the solution, as an object, to it.
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream decompress = new ObjectInputStream(inFromClient);
            Maze maze =null;
            while(maze==null){
                maze= (Maze)decompress.readObject();
            }
            String filePath =this.pathToDir + Integer.toString(maze.toString().hashCode());
            File newFile = new File(filePath);
            Solution sol=null;
            if(newFile.exists()){ //the maze has been solved before and the solution is saved. No need to solve it again.
                ObjectInputStream inMazeSol = new ObjectInputStream(new FileInputStream(newFile));
                ArrayList<AState> aSol =(ArrayList<AState>)inMazeSol.readObject();
                sol = new Solution(aSol);
                inMazeSol.close();
            }
            else{ //first time solving the maze.
                ISearchable is = new SearchableMaze(maze);
                Class<?> search = Class.forName("algorithms.search."+Configurations.getInstance().getMazeSearchingAlgorithm());
                ISearchingAlgorithm searchAlg = (ISearchingAlgorithm)search.getDeclaredConstructor().newInstance();
                sol = searchAlg.solve(is);
                ///write to dir the solution
                ObjectOutputStream outMazeSol = new ObjectOutputStream(new FileOutputStream(newFile));
                outMazeSol.writeObject(sol.getSolutionPath());
                outMazeSol.flush();
                outMazeSol.close();
                ///
            }
            //send solution to client.
            ObjectOutputStream toServer = new ObjectOutputStream(outToClient);
            toServer.writeObject(sol);
            toServer.flush();
            toServer.close();
            decompress.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
