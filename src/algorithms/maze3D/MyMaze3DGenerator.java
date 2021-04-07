package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{
    private int[][][] maze;
    private Position3D start;
    private Position3D goal;

    @Override
    public Maze3D generate(int depth, int row, int column) {
        Maze3D maze = onesMaze(depth,row,column);
        this.dfsMaze3D(maze);
        return maze;
    }

    private Maze3D onesMaze(int _k, int _n, int _m) {
        int[][][] maze = new int[_k][_n][_m];
        for(int k=0;k<_k;k++){
            for(int i=0;i<_n;i++){
                for (int j = 0; j <_m ; j++) {
                    maze[k][i][j]=1;
                }
            }
        }
        int fk=_k-1;
        if(_k%2==0){
            fk=_k-2;
        }
        Random r = new Random();
        int si = r.nextInt((int)_n / 2);
        if (si%2 != (_n-1)%2)
            si+=1;
        int fj = ((int)_m/2) + r.nextInt((int)_m / 2) - 1;
        if (fj%2 != 0)
            fj-=1;
        Position3D start=new Position3D(0,si, 0);
        Position3D goal=new Position3D(fk,_n-1,fj);
        return new Maze3D(start,goal,maze);
    }

    private void dfsMaze3D(Maze3D maze){
        int[][][] visited=new int[maze.getK()][maze.getN()][maze.getM()];
        Stack<Position3D> s = new Stack<>();
        Position3D curr=maze.getStartPosition3D(), goal=maze.getGoalPosition3D();
        maze.setPlaceInMaze3D(curr,0);
        visited[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()]=1;
        s.push(curr);
        while(!s.isEmpty()){
            curr=s.pop();
            Position3D temp=this.checkVisited(visited,this.getNeighbors(maze,curr));
            if(!(temp==null)){
                s.push(curr);
                maze.setPlaceInMaze3D(temp,0);
                int k=curr.getDepthIndex(), i= curr.getRowIndex(), j=curr.getColumnIndex();
                if(curr.getDepthIndex()!=temp.getDepthIndex()){
                    if(curr.getDepthIndex()<temp.getDepthIndex()) k=curr.getDepthIndex()+1;
                    else k=curr.getDepthIndex()-1;
                }
                else if(curr.getRowIndex()!=temp.getRowIndex()){
                    if(curr.getRowIndex()<temp.getRowIndex()) i=curr.getRowIndex()+1;
                    else i=curr.getRowIndex()-1;
                }
                else{
                    if(curr.getColumnIndex()<temp.getColumnIndex()) j=curr.getColumnIndex()+1;
                    else j=curr.getColumnIndex()-1;
                }
                maze.setPlaceInMaze3D(new Position3D(k, i,j), 0);
                visited[k][i][j]=1;
                visited[temp.getDepthIndex()][temp.getRowIndex()][temp.getColumnIndex()]=1;
                visited[temp.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()]=1;
                s.push(temp);
            }
        }
    }

    private ArrayList<Position3D> getNeighbors(Maze3D maze, Position3D p){
        ArrayList<Position3D> to_ret = new ArrayList<>();
        if(p.getRowIndex()>1)//up exist
            to_ret.add(new Position3D(p.getDepthIndex(), p.getRowIndex()-2,p.getColumnIndex()));
        if(p.getRowIndex()<maze.getN()-2)//down exist
            to_ret.add(new Position3D(p.getDepthIndex(), p.getRowIndex()+2,p.getColumnIndex()));
        if(p.getColumnIndex()>1)//left exist
            to_ret.add(new Position3D(p.getDepthIndex(), p.getRowIndex(),p.getColumnIndex()-2));
        if(p.getColumnIndex()<maze.getM()-2)//right exist
            to_ret.add(new Position3D(p.getDepthIndex(), p.getRowIndex(),p.getColumnIndex()+2));
        if(p.getDepthIndex()>1)//out exist
            to_ret.add(new Position3D(p.getDepthIndex()-2, p.getRowIndex(),p.getColumnIndex()));
        if(p.getDepthIndex()<maze.getK()-2)//in exist
            to_ret.add(new Position3D(p.getDepthIndex()+2, p.getRowIndex(),p.getColumnIndex()));
        return to_ret;
    }

    private Position3D checkVisited(int[][][] visited,ArrayList<Position3D> lst){
        ArrayList<Position3D> temp = (ArrayList<Position3D>) lst.clone();
        for (Position3D p: lst) {
            if(visited[p.getDepthIndex()][p.getRowIndex()][p.getColumnIndex()]==1)
                temp.remove(p);
        }
        if(temp.isEmpty())
            return null;
        Random r = new Random();
        int rand = r.nextInt(temp.size());
        return temp.get(rand);
    }

    public int[][][] getMap() {
        return maze;
    }

    public void setMap(int[][][] maze) {
        this.maze = maze;
    }

    public Position3D getStartPosition() {
        return start;
    }

    public void setStartPosition(Position3D start) {
        this.start = start;
    }

    public Position3D getGoalPosition() {
        return goal;
    }

    public void setGoalPosition(Position3D goal) {
        this.goal = goal;
    }

}
