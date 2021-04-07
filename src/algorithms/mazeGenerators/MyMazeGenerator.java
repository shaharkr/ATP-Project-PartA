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
        int fj = ((int)m/2) + r.nextInt((int)m / 2) - 1;
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
}
