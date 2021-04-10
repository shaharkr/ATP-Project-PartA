package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * SearchableMaze is a class adapting the ISearchable interface
 * to the maze problem.
 */
public class SearchableMaze implements ISearchable{
    Maze maze;

    /**
     * @param maze the maze that is being adapted to ISearchable.
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    //getter methods.
    @Override
    public AState getStartState() {
        Position p =maze.getStartPosition();
        return new MazeState(p,0,p.toString());
    }

    @Override
    public AState getGoalState() {
        Position p =maze.getGoalPosition();
        return new MazeState(p,0,p.toString());
    }

    /**
     * @param as a certain state in the searchable problem.
     * @return list of states that can be reached from as.
     * @throws Exception if as is null.
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState as) throws Exception{
        if (as==null) throw new Exception("Cannot find successors of null");
        MazeState ms = (MazeState)as;
        int i =ms.getPosition().getRowIndex(), j = ms.getPosition().getColumnIndex();
        boolean up=false, down=false, left=false, right=false;
        ArrayList<AState> to_ret = new ArrayList<>();
        if (i!=0 && maze.getMaze()[i-1][j]==0){//up
            Position p = new Position(i-1,j);
            to_ret.add(new MazeState(p, 10 ,p.toString()));
            up=true;
        }
        if(j!=0 && maze.getMaze()[i][j-1]==0) {//left
            Position p = new Position(i,j-1);
            to_ret.add(new MazeState(new Position(i, j - 1), 10, p.toString()));
            left=true;
        }
        if(i!=maze.getN()-1 && maze.getMaze()[i+1][j]==0) {//down
            Position p =new Position(i+1,j);
            to_ret.add(new MazeState(p,  10, p.toString()));
            down=true;
        }
        if(j!=maze.getM()-1 && maze.getMaze()[i][j+1]==0) {//right
            Position p =new Position(i, j + 1);
            to_ret.add(new MazeState(p, 10, p.toString()));
            right=true;
        }
        if(i!=0 && j!=0 && maze.getMaze()[i-1][j-1]==0 && (up || left)){
            Position p =new Position(i-1, j - 1);
            to_ret.add(new MazeState(p,  15, p.toString()));
        }
        if(i!=0 && j!= maze.getM()-1 && maze.getMaze()[i-1][j+1]==0 && (up || right)){
            Position p =new Position(i-1, j+1);
            to_ret.add(new MazeState(p, 15, p.toString()));
        }
        if(i!= maze.getN()-1 && j!=0 && maze.getMaze()[i+1][j-1]==0 && (down || left)){
            Position p =new Position(i+1, j-1);
            to_ret.add(new MazeState(p,  15, p.toString()));
        }
        if(i!= maze.getN()-1 && j!= maze.getM()-1 && maze.getMaze()[i+1][j+1]==0 && (down || right)){
            Position p =new Position(i+1, j+1);
            to_ret.add(new MazeState(p, 15, p.toString()));
        }
        return to_ret;
    }


}
