package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze {

    private int[][] maze;
    private Position start;
    private Position goal;
    private int n, m;

    public Maze(int n, int m) {
        this.n=n;
        this.m=m;
        maze = new int[n][m];
        this.start=new Position(0,0);
        this.goal=new Position(n-1,m-1);
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setPlaceInMaze(Position pos, int num) {
        maze[pos.getI()][pos.getJ()]=num;
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start.setI(start.getI());
        this.start.setJ(start.getJ());
    }

    public Position getGoal() {
        return goal;
    }

    public void setGoal(Position goal) {
        this.goal.setI(goal.getI());
        this.goal.setJ(goal.getJ());
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void print(){
        for (int i = 0; i <this.getN() ; i++) {
            System.out.println(Arrays.toString(this.getMaze()[i]));
        }
        for(int i=0; i<this.getN(); i++){
            for(int j=0;j<this.getM(); j++){
                if(i==start.getI() && j==start.getJ()){
                    System.out.println("S");
                    continue;
                }
                if(i==goal.getI() && j==goal.getJ()){
                    System.out.println("D");
                    continue;
                }
                if(this.getMaze()[i][j] == 1)
                    System.out.print("\u2b1b");
                else
                    System.out.print("\u2B1C");
            }
        }
    }
}
