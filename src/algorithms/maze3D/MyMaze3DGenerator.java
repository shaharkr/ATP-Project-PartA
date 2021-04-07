package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    @Override
    public Maze3D generate(int depth, int row, int column) {
        int[][][] m = new int[depth][row][column];
        for(int k=0;k<depth;k++){
            for(int i=0;i<row;i++){
                for (int j = 0; j <column ; j++) {
                    m[k][i][j]=1;
                }
            }
        }
        Position3D start = new Position3D(0,0,0);
        Position3D goal = new Position3D(depth-1,row-1,column-1);
        Maze3D maze = new Maze3D(start,goal,m);
        this.dfsMaze3D(maze);
        return maze;
    }

    private void dfsMaze3D(Maze3D maze){
        int[][][] visited=new int[maze.getK()][maze.getN()][maze.getM()];
        Stack<Position3D> s = new Stack<>();
        Position3D curr=maze.getStartPosition3D(), goal=maze.getGoalPosition3D();
        maze.setPlaceInMaze3D(curr,0);
        visited[curr.getK()][curr.getI()][curr.getJ()]=1;
        s.push(curr);
        while(!s.isEmpty()){
            curr=s.pop();
            Position3D temp=this.checkVisited(visited,this.getNeighbors(maze,curr));
            if(!(temp==null)){
                s.push(curr);
                maze.setPlaceInMaze3D(temp,0);
                int k=curr.getK(), i= curr.getI(), j=curr.getJ();
                if(curr.getK()!=temp.getK()){
                    if(curr.getK()<temp.getK()) k=curr.getI()+1;
                    else k=curr.getK()-1;
                }
                else if(curr.getI()!=temp.getI()){
                    if(curr.getI()<temp.getI()) i=curr.getI()+1;
                    else i=curr.getI()-1;
                }
                else{
                    if(curr.getJ()<temp.getJ()) j=curr.getJ()+1;
                    else j=curr.getJ()-1;
                }
                maze.setPlaceInMaze3D(new Position3D(k, i,j), 0);
                visited[k][i][j]=1;
                visited[temp.getK()][temp.getI()][temp.getJ()]=1;
                visited[temp.getK()][curr.getI()][curr.getJ()]=1;
                s.push(temp);
            }
        }
    }

    private ArrayList<Position3D> getNeighbors(Maze3D maze, Position3D p){
        ArrayList<Position3D> to_ret = new ArrayList<>();
        if(p.getI()>1)//up exist
            to_ret.add(new Position3D(p.getK(), p.getI()-2,p.getJ()));
        if(p.getI()<maze.getN()-2)//down exist
            to_ret.add(new Position3D(p.getK(), p.getI()+2,p.getJ()));
        if(p.getJ()>1)//left exist
            to_ret.add(new Position3D(p.getK(), p.getI(),p.getJ()-2));
        if(p.getJ()<maze.getM()-2)//right exist
            to_ret.add(new Position3D(p.getK(), p.getI(),p.getJ()+2));
        if(p.getK()>1)//out exist
            to_ret.add(new Position3D(p.getK()-2, p.getI(),p.getJ()));
        if(p.getK()<maze.getK()-2)//in exist
            to_ret.add(new Position3D(p.getK()+2, p.getI(),p.getJ()));
        return to_ret;
    }

    private Position3D checkVisited(int[][][] visited,ArrayList<Position3D> lst){
        ArrayList<Position3D> temp = (ArrayList<Position3D>) lst.clone();
        for (Position3D p: lst) {
            if(visited[p.getK()][p.getI()][p.getJ()]==1)
                temp.remove(p);
        }
        if(temp.isEmpty())
            return null;
        Random r = new Random();
        int rand = r.nextInt(temp.size());
        return temp.get(rand);
    }
}
