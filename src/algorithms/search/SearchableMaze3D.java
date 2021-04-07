package algorithms.search;

import algorithms.maze3D.Maze3D;
import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class SearchableMaze3D implements ISearchable{
    private Maze3D maze;

    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        Position3D p =maze.getStartPosition3D();
        return new Maze3DState(p,0,p.toString());
    }

    @Override
    public AState getGoalState() {
        Position3D p =maze.getGoalPosition3D();
        return new Maze3DState(p,0,p.toString());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState as) {
        if (as==null) return null;
        Maze3DState ms = (Maze3DState)as;
        int k=ms.getPosition().getDepthIndex(), i =ms.getPosition().getRowIndex(), j = ms.getPosition().getColumnIndex();
        ArrayList<AState> to_ret = new ArrayList<>();
        if (k!=maze.getK()-1 && maze.getMaze()[k+1][i][j]==0){//in
            Position3D p = new Position3D(k+1,i,j);
            to_ret.add(new Maze3DState(p, 10 ,p.toString()));
        }
        if(k!=0 && maze.getMaze()[k-1][i][j]==0) {//out
            Position3D p = new Position3D(k-1,i,j);
            to_ret.add(new Maze3DState(p, 10, p.toString()));
        }
        if (i!=0 && maze.getMaze()[k][i-1][j]==0){//up
            Position3D p = new Position3D(k,i-1,j);
            to_ret.add(new Maze3DState(p, 10 ,p.toString()));
        }
        if(i!=maze.getN()-1 && maze.getMaze()[k][i+1][j]==0) {//down
            Position3D p =new Position3D(k,i+1,j);
            to_ret.add(new Maze3DState(p,  10, p.toString()));
        }
        if(j!=0 && maze.getMaze()[k][i][j-1]==0) {//left
            Position3D p = new Position3D(k,i,j-1);
            to_ret.add(new Maze3DState(p, 10, p.toString()));
        }
        if(j!=maze.getM()-1 && maze.getMaze()[k][i][j+1]==0) {//right
            Position3D p =new Position3D(k,i, j + 1);
            to_ret.add(new Maze3DState(p, 10, p.toString()));
        }
        return to_ret;

    }

    @Override
    public ArrayList<AState> getAllAStates() {
        return null;
    }

    public Maze3D getMaze() {
        return maze;
    }

    public void setMaze(Maze3D maze) {
        this.maze = maze;
    }
}