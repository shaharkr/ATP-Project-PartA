package algorithms.test;
import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
       /* maze.setPlaceInMaze(new Position(1,2),1);
        maze.setPlaceInMaze(new Position(1,3),1);
        maze.setPlaceInMaze(new Position(2,3),1);
        maze.setPlaceInMaze(new Position(2,0),1);
        maze.setPlaceInMaze(new Position(3,0),1);
        maze.setPlaceInMaze(new Position(3,1),1);
        maze.setStart(new Position(0,0));
        maze.setGoal(new Position(2,4));
       /* IMazeGenerator3D mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(100, 100,100);*/
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        //solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        System.out.println(searcher.measureAlgorithmTimeMillis(domain));
        /*System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }*/
    }
}
