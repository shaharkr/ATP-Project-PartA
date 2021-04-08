package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    @Override
    public Maze3D generate(int depth, int row, int column) throws Exception {
        if(depth<0 || row<0 || column<0){
            throw new Exception("Invalid inputs: depth, row and column need to be positive integers\n");
        }
        int[][][] maze = new int[depth][row][column];
        //updating the entire maze to 1's(walls).
        for(int k=0;k<depth;k++){
            for(int i=0;i<row;i++){
                for (int j = 0; j <column ; j++) {
                    maze[k][i][j]=1;
                }
            }
        }
        int fk=depth-1;
        if(depth%2==0){
            fk=depth-2;
        }
        //randomizing start and end position in a way fitting to the algorithm, and not colliding
        Random r = new Random();
        int si = r.nextInt((int)row / 2);
        if (si%2 != (row-1)%2)
            si+=1;
        int fj = ((int)column/2) + r.nextInt((int)column / 2) - 1;
        if (fj%2 != 0)
            fj-=1;
        Position3D start=new Position3D(0,si, 0);
        Position3D goal=new Position3D(fk,row-1,fj);
        Maze3D new_maze = new Maze3D(start,goal,maze);
        this.dfsMaze3D(new_maze);
        return new_maze;
    }

    /**
     * @param maze receives maze full of walls(1's) and breaks the walls according to dfs algorithm.
     */
    private void dfsMaze3D(Maze3D maze){
        int[][][] visited=new int[maze.getK()][maze.getN()][maze.getM()];
        Stack<Position3D> s = new Stack<>();
        Position3D curr=maze.getStartPosition3D(), goal=maze.getGoalPosition3D();
        try{maze.setPlaceInMaze3D(curr,0);}
        catch (Exception e){System.out.println(e.getMessage());}
        visited[curr.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()]=1;
        s.push(curr);
        while(!s.isEmpty()){
            curr=s.pop();
            Position3D temp=this.checkVisited(visited,this.getNeighbors(maze,curr));
            if(!(temp==null)){
                s.push(curr);
                try{maze.setPlaceInMaze3D(temp,0);}
                catch (Exception e){System.out.println(e.getMessage());}
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
                try{maze.setPlaceInMaze3D(new Position3D(k, i,j), 0);}
                catch (Exception e){System.out.println(e.getMessage());}
                visited[k][i][j]=1;
                visited[temp.getDepthIndex()][temp.getRowIndex()][temp.getColumnIndex()]=1;
                visited[temp.getDepthIndex()][curr.getRowIndex()][curr.getColumnIndex()]=1;
                s.push(temp);
            }
        }

        if(maze.getGoalPosition3D().getDepthIndex()==maze.getK()-2){
            this.randomlyZeroes(maze);
        }
    }

    private void randomlyZeroes(Maze3D maze) {
        //in case of sync problem with 3D maze break some random walls at last board.
        Random r = new Random();
        int num_of_zero= (int)((maze.getN()*maze.getM())/3);
        for (int i = 0; i < num_of_zero; i++) {
            int row = r.nextInt(maze.getN());
            int col = r.nextInt(maze.getM());
            try{maze.setPlaceInMaze3D(new Position3D(maze.getK()-1,row,col),0);}
            catch (Exception e){System.out.println(e.getMessage());}
        }
    }

    /**
     * @param maze = 3D maze
     * @param p = specific position in maze
     * @return list of all 3D positions maze is capable of reaching.
     */
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

    /**
     * @param visited 3D array whereas if visited(k,i,j)=1 then (i,k,j) was already found via dfs_visit
     * @param lst list of positions in neighborhood with a certain position
     * @return randomized position of lst that has not been visited by dfs_visit
     */
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


}
