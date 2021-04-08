package algorithms.mazeGenerators;

import algorithms.search.AState;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int n, int m) throws Exception {
        if(n<0 || m<0){
            throw new Exception("Invalid inputs: n and m need to be positive integers\n");
        }
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
        Maze new_maze = new Maze(start,goal,maze);
        this.dfsMaze(new_maze);
        return new_maze;
    }

    /**
     * @param maze is a maze update to only walls(1's).
     * The algorithm breaks the walls to every direction possible as long as the position in the said
     * direction has not been visited before
     */
    private void dfsMaze(Maze maze){
        int[][] visited=new int[maze.getN()][maze.getM()];
        Stack<Position> s = new Stack<>();
        Position curr=maze.getStartPosition(), goal=maze.getGoalPosition();
        try{ maze.setPlaceInMaze(curr,0);}
        catch (Exception e){System.out.println(e.getMessage());}
        visited[curr.getI()][curr.getJ()]=1;
        s.push(curr);
        while(!s.isEmpty()){
            curr=s.pop();
            Position temp=this.checkVisited(visited,this.getNeighbors(maze,curr));
            if(!(temp==null)){
                s.push(curr);
                try{maze.setPlaceInMaze(temp,0);}
                catch (Exception e){System.out.println(e.getMessage());}
                int i= curr.getI(), j=curr.getJ();
                if(curr.getI()==temp.getI()){
                    if(curr.getJ()<temp.getJ()) j=curr.getJ()+1;
                    else j=curr.getJ()-1;
                }
                else{
                    if(curr.getI()<temp.getI()) i=curr.getI()+1;
                    else i=curr.getI()-1;
                }
                try{maze.setPlaceInMaze(new Position(i,j), 0);}
                catch (Exception e){System.out.println(e.getMessage());}
                visited[i][j]=1;
                visited[temp.getI()][temp.getJ()]=1;
                visited[curr.getI()][curr.getJ()]=1;
                s.push(temp);
            }
        }
    }

    /**
     * @param maze the maze we want to generate in generate function
     * @param p current position in maze
     * @return list of positions that p is capable of reaching.
     */
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

    /**
     * @param visited 2D array of in which for every position (i,j) if the position has been visited than visited(i,j)=1.
     * @param lst list of positions that can be reached from certain position.
     * @return a random position of list that has not been visited yet.
     */
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
