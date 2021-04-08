package algorithms.test;

import algorithms.maze3D.*;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        try{ IMazeGenerator mg = new EmptyMazeGenerator();
            Maze maze = mg.generate(4,5);
            maze.setPlaceInMaze(new Position(2,0),1);
            maze.setPlaceInMaze(new Position(3,0),1);
            maze.setPlaceInMaze(new Position(3,1),1);
            maze.setPlaceInMaze(new Position(1,2),1);
            maze.setPlaceInMaze(new Position(1,3),1);
            maze.setPlaceInMaze(new Position(2,3),1);
            maze.setGoal(new Position(2,4));
            maze.print();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());
        }
        catch (Exception e){System.out.println(e.getMessage());}
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) throws Exception{
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));//Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }

}
