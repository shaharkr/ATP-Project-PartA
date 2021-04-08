package algorithms.search;

import algorithms.maze3D.Maze3D;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    BestFirstSearch bestFS=new BestFirstSearch();

    @Test
    public void getName() throws Exception{

       assertEquals("BestFirstSearch",bestFS.getName());
    }

    @Test
    public void getNumberOfNodesEvaluated() throws Exception{
        AMazeGenerator amg = new EmptyMazeGenerator();
        Maze maze= amg.generate(2,2);
        maze.setGoal(new Position(0,0));
        ISearchable searchable=new SearchableMaze(maze);
        assertEquals(bestFS.getNumberOfNodesEvaluated(),0);
        bestFS.measureAlgorithmTimeMillis(searchable);
        assertEquals(bestFS.getNumberOfNodesEvaluated(),1);
    }

    //////////////////////////////////////////////////////////////////
    public void solve() throws Exception{
        bestFS.solve(null);
    }
    ////////////////////////////////////////////////////////////////

    @Test
    public void measureAlgorithmTimeMillis() throws Exception{
        AMazeGenerator amg = new MyMazeGenerator();
        Maze maze= amg.generate(1000,1000);
        ISearchable searchable=new SearchableMaze(maze);
        assertFalse(bestFS.measureAlgorithmTimeMillis(searchable)>60000);
    }
}