package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * MyMazeGenerator is a specific 2D maze generator, it extends AMazeGenerator so measureTimeInMillis is
 * already implemented, it implements the generate itself using a randomized dfs visit algorithm.
 */
public class MyMazeGenerator extends AMazeGenerator{
    @Override
    /**
     * @param n amount of rows in maze created
     * @param m amount of columns in maze created
     * @return 2D maze generated with randomized dfs visit algorithm.
     * @throws Exception if input size is illegal.
     */
    public Maze generate(int n, int m) throws Exception {
        if(n<0 || m<0){
            throw new Exception("Invalid inputs: n and m need to be positive integers\n");
        }
        int[][] maze = new int[n][m];
        if(n<2 || m<2){
            IMazeGenerator sm= new SimpleMazeGenerator();
            return sm.generate(n,m);
        }
        //first create an all wall maze
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maze[i][j]=1;
            }
        }
        //choose the start and gaol positions randomly, force them to be compatible with the algorithm
        Random r = new Random();
        int si = r.nextInt((int)n / 2);
        if (si%2 != (n-1)%2)
            si+=1;
        int fj = ((int)m/2) + r.nextInt((int)m / 2) ;
        if (fj%2 != 0 && n!=2)
            fj-=1;
        Position start=new Position(si,0);
        Position goal=new Position(n-1,fj);
        Maze new_maze = new Maze(start,goal,maze);
        //dfs maze will break the walls to create paths
        this.dfsMaze(new_maze);
        return new_maze;
    }

    /**
     * @param maze is a maze update to only walls(1's), and breaks the walls
     * according to iterative dfs algorithm, using a stack.
     * The algorithm breaks the walls to every direction possible as long as the position in the said
     * direction has not been visited before
     */
    private void dfsMaze(Maze maze){
        //visited is a 2D matrix showing 1 where the algorithm has visited before, otherwise 0.
        int[][] visited=new int[maze.getN()][maze.getM()];
        Stack<Position> s = new Stack<>();
        Position curr=maze.getStartPosition(), goal=maze.getGoalPosition();
        try{ maze.setPlaceInMaze(curr,0);}
        catch (Exception e){System.out.println(e.getMessage());}
        visited[curr.getRowIndex()][curr.getColumnIndex()]=1;
        s.push(curr);
        while(!s.isEmpty()){
            curr=s.pop();
            Position temp=this.checkVisited(visited,this.getNeighbors(maze,curr));
            if(!(temp==null)){
                s.push(curr);
                try{maze.setPlaceInMaze(temp,0);}
                catch (Exception e){System.out.println(e.getMessage());}
                //create new position according to the difference between curr and temp.
                int i= curr.getRowIndex(), j=curr.getColumnIndex();
                if(curr.getRowIndex()==temp.getRowIndex()){
                    if(curr.getColumnIndex()<temp.getColumnIndex()) j=curr.getColumnIndex()+1;
                    else j=curr.getColumnIndex()-1;
                }
                else{
                    if(curr.getRowIndex()<temp.getRowIndex()) i=curr.getRowIndex()+1;
                    else i=curr.getRowIndex()-1;
                }
                try{maze.setPlaceInMaze(new Position(i,j), 0);}
                catch (Exception e){System.out.println(e.getMessage());}
                visited[i][j]=1;
                visited[temp.getRowIndex()][temp.getColumnIndex()]=1;
                visited[curr.getRowIndex()][curr.getColumnIndex()]=1;
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
        if(p.getRowIndex()>1)//up exist
            to_ret.add(new Position(p.getRowIndex()-2,p.getColumnIndex()));
        if(p.getRowIndex()<maze.getN()-2)//down exist
            to_ret.add(new Position(p.getRowIndex()+2,p.getColumnIndex()));
        if(p.getColumnIndex()>1)//left exist
            to_ret.add(new Position(p.getRowIndex(),p.getColumnIndex()-2));
        if(p.getColumnIndex()<maze.getM()-2)//right exist
            to_ret.add(new Position(p.getRowIndex(),p.getColumnIndex()+2));
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
            if(visited[p.getRowIndex()][p.getColumnIndex()]==1)
                temp.remove(p);
        }
        if(temp.isEmpty())
            return null;
        Random r = new Random();
        int rand = r.nextInt(temp.size());
        return temp.get(rand);
    }
}
