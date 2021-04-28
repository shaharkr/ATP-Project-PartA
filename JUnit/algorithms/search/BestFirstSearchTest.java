package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this JUnit test class checks the basic functionality of the best first search algorithm.
 */
class BestFirstSearchTest {
    BestFirstSearch bestFS=new BestFirstSearch();

    //check that the name was initiated correctly.
    @Test
    public void getName() throws Exception{
       assertEquals("BestFirstSearch",bestFS.getName());
    }

    //this method checks that numberOfNodesEvaluated is initiated to 0,
    //and that when start=goal, the numberOfNodesEvaluated is returned to 1.
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

    //checks that the error message for trying to solve null is correct.
    @Test
    public void solve() throws Exception{
        Exception e = assertThrows(Exception.class,()->{bestFS.solve(null);});
        String message = e.getMessage();
        String expectedMessage = "Cannot solve null, searchable can't be null\n";
        assertTrue(message.equals(expectedMessage));
    }

    //check that the algorithm creates a 1000X1000 maze in under a minute.
    @Test
    public void measureAlgorithmTimeMillis() throws Exception{
        AMazeGenerator amg = new MyMazeGenerator();
        Maze maze= amg.generate(1000,1000);
        ISearchable searchable=new SearchableMaze(maze);
        assertFalse(bestFS.measureAlgorithmTimeMillis(searchable)>60000);
    }
}
