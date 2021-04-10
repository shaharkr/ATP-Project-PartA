package test;

import algorithms.maze3D.*;
import algorithms.search.*;
import java.util.ArrayList;

public class RunSearchOnMaze3D {

    public static void main(String[] args) {
        try{
            IMaze3DGenerator mg = new MyMaze3DGenerator();
            Maze3D maze = mg.generate(100,100, 100);
            maze.print();
            SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
            solveProblem(searchableMaze, new BreadthFirstSearch());
            solveProblem(searchableMaze, new DepthFirstSearch());
            solveProblem(searchableMaze, new BestFirstSearch());}
        catch (Exception e){System.out.println(e.getMessage());}
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        try{
            //Solve a searching problem with a searcher
            Solution solution = searcher.solve(domain);
            System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
            //Printing Solution Path
            System.out.println("Solution path:");
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            for (int i = 0; i < solutionPath.size(); i++) {
                System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
            }}
        catch (Exception e){System.out.println(e.getMessage());}
    }
}

