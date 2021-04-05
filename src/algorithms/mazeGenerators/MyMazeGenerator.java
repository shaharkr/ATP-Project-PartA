package algorithms.mazeGenerators;

import algorithms.search.AState;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int n, int m) {
        Maze maze = this.onesMaze(n,m);
        this.dfsMaze(maze);
        return maze;
    }

    public Maze onesMaze(int n, int m) {
        int[][] maze = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maze[i][j]=1;
            }
        }
        Random r = new Random();
        int si = r.nextInt((int)n / 2);
        if (si%2 != (n-1)%2)
            si+=1;
        int fj = (int)m/2 + r.nextInt((int)m / 2) - 1;
        if (fj%2 != 0)
            fj-=1;
        Position start=new Position(si,0);
        Position goal=new Position(n-1,fj);
        return new Maze(start,goal,maze);
    }

    private void dfsMaze(Maze maze){
        int[][] visited=new int[maze.getN()][maze.getM()];
        Stack<Position> s = new Stack<>();
        Position curr=maze.getStartPosition(), goal=maze.getGoalPosition();
        maze.setPlaceInMaze(curr,0);
        visited[curr.getI()][curr.getJ()]=1;
        s.push(curr);
        while(!s.isEmpty()){
            curr=s.pop();
            Position temp=this.checkVisited(visited,this.getNeighbors(maze,curr));
            if(!(temp==null)){
                s.push(curr);
                maze.setPlaceInMaze(temp,0);
                int i= curr.getI(), j=curr.getJ();
                if(curr.getI()==temp.getI()){
                    if(curr.getJ()<temp.getJ()) j=curr.getJ()+1;
                    else j=curr.getJ()-1;
                }
                else{
                    if(curr.getI()<temp.getI()) i=curr.getI()+1;
                    else i=curr.getI()-1;
                }
                maze.setPlaceInMaze(new Position(i,j), 0);
                visited[i][j]=1;
                visited[temp.getI()][temp.getJ()]=1;
                visited[curr.getI()][curr.getJ()]=1;
                s.push(temp);
            }
        }
    }

    private ArrayList<Position> getNeighbors(Maze maze, Position p){
        ArrayList<Position> to_ret = new ArrayList<>();
        if(p.getI()>1)//up exist
            to_ret.add(new Position(p.getI()-2,p.getJ()));
        if(p.getI()<maze.getN()-2)//down exist
            to_ret.add(new Position(p.getI()+2,p.getJ()));
        if(p.getJ()>1)//left exist
            to_ret.add(new Position(p.getI(),p.getJ()-2));
        if(p.getJ()<maze.getM()-2)//right exist
            to_ret.add(new Position(p.getI(),p.getJ()+2));
        return to_ret;
    }

    private Position checkVisited(int[][] visited,ArrayList<Position> lst){
        ArrayList<Position> temp = (ArrayList<Position>) lst.clone();
        for (Position p: lst) {
            if(visited[p.getI()][p.getJ()]==1)
                temp.remove(p);
        }
        if(temp.isEmpty())
            return null;
        Random r = new Random();
        int rand = r.nextInt(temp.size());
        return temp.get(rand);
    }





























    private void divide( Maze maze, int i1, int j1,int i2, int j2,int[][] gates_arr){
        if( (i2-i1<2 && j2-j1<2) || i2-i1<=0 || j2-j1<=0 ) { return; }
        Random r = new Random();
        int i = 0;
        if(howToCut(i2-i1,j2-j1)==Orientation.HORIZONTAL){
            int row =r.nextInt(i2-i1) +i1,gate = r.nextInt(j2-j1)+j1;
            while((!this.checkFrameGatesHORIZONTAL(gates_arr,gate,j1,j2) || !this.checkValidGate(maze,Orientation.HORIZONTAL,row,gate)) && i!=100){
                row =r.nextInt(i2-i1) +i1;
                gate = r.nextInt(j2-j1)+j1;
                i++;
            }
            for(int k=j1 ;k<j2;k++){
                Position pos = new Position(row,k);
                maze.setPlaceInMaze(pos,1);
            }
            Position pos = new Position(row,gate);
            maze.setPlaceInMaze(pos,0);
            gates_arr[row+1][gate+1]=1;
            divide(maze,i1,j1,row-1,j2,gates_arr);
            divide(maze,row+1,j1,i2,j2,gates_arr);
        }
        else{
            int col = r.nextInt(j2-j1) +j1, gate =r.nextInt(i2-i1) +i1;
            while((!this.checkFrameGatesHORIZONTAL(gates_arr,gate,i1,i2) || !this.checkValidGate(maze,Orientation.VERTICAL,col,gate)) && i!=100){
                col = r.nextInt(j2-j1) +j1;
                gate =r.nextInt(i2-i1) +i1;
                i++;
            }
            for(int k=i1 ;k<i2;k++){
                Position pos = new Position(k,col);
                maze.setPlaceInMaze(pos,1);
            }
            Position pos = new Position(gate,col);
            maze.setPlaceInMaze(pos,0);
            gates_arr[gate+1][col+1]=1;
            divide(maze,i1,j1,i2,col-1,gates_arr);
            divide(maze,i1,col+1,i2,j2,gates_arr);
        }
    }

    private Orientation howToCut(int n, int m){
        if (n<m)
            return Orientation.VERTICAL;
        else
            return Orientation.HORIZONTAL;
    }

    private boolean checkFrameGatesVERTICAL(int[][] gate_arr, int color, int i1, int i2){
        if(!(gate_arr[i1][color]==1) && !(gate_arr[i2+2][color]==1)){
            return true;
        }
        return false;
    }

    private boolean checkFrameGatesHORIZONTAL(int[][] gate_arr, int color, int j1, int j2){
        if(gate_arr[color][j1]==0 && gate_arr[color][j2+2]==0){
            return true;
        }
        return false;
    }

    private boolean checkValidGate(Maze maze, Orientation kind, int row, int col){
        if(kind==Orientation.HORIZONTAL){
            boolean up, down;
            if(row==0) up=true;
            else up=maze.getMaze()[row-1][col]==0;
            if(row==maze.getN()-1) down=true;
            else down=maze.getMaze()[row+1][col]==0;
            if( up && down ){
                return true;
            }
        }
        else{
            boolean left, right;
            if(col==0) left=true;
            else left=maze.getMaze()[row][col-1]==0;
            if(col==maze.getM()-1) right=true;
            else right=maze.getMaze()[row][col+1]==0;
            if(left && right){
                return true;
            }
        }
        return false;
    }
}
