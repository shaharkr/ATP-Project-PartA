package algorithms.mazeGenerators;

import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int n, int m) {
        EmptyMazeGenerator emg = new EmptyMazeGenerator();
        Maze maze = emg.generate(n,m);
        this.divide(maze, 0, 0,m-1,n-1);
        return maze;
    }

    private void divide( Maze maze, int i1, int j1,int i2, int j2){
        if((i2-i1<2 && j2-j1<2) || i2-i1<=0 || j2-j1<=0) {
            return;
        }
        Random r = new Random();
        if(howToCut(i2-i1,j2-j1)==Orientation.HORIZONTAL){
            int row =r.nextInt(i2-i1) +i1;
            for(int k=j1 ;k<j2;k++){
                Position pos = new Position(row,k);
                maze.setPlaceInMaze(pos,1);
            }
            int gate = r.nextInt(j2-j1)+j1;
            Position pos = new Position(row,gate);
            maze.setPlaceInMaze(pos,0);
            divide(maze,i1,j1,row-1,j2);
            divide(maze,row+1,j1,i2,j2);
        }
        else{
            int col = r.nextInt(j2-j1) +j1;
            for(int k=i1 ;k<i2;k++){
                Position pos = new Position(k,col);
                maze.setPlaceInMaze(pos,1);
            }
            int gate =r.nextInt(i2-i1) +i1;
            Position pos = new Position(gate,col);
            maze.setPlaceInMaze(pos,0);
            divide(maze,i1,j1,i2,col-1);
            divide(maze,i1,col+1,i2,j2);
        }
    }

    private Orientation howToCut(int n, int m){
        if (n<m)
            return Orientation.VERTICAL;
        else
            return Orientation.HORIZONTAL;
    }
}
