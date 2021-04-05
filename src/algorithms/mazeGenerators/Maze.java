package algorithms.mazeGenerators;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class Maze {

    private int[][] maze;
    private Position start;
    private Position goal;

    public Maze(Position start, Position goal, int[][] maze) {
        this.maze=maze;
        this.start=start;
        this.goal=goal;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setPlaceInMaze(Position pos, int num) {
        maze[pos.getI()][pos.getJ()]=num;
    }

    public Position getStartPosition() {
        return start;
    }

    public void setStart(Position start) {
        this.start.setI(start.getI());
        this.start.setJ(start.getJ());
    }

    public Position getGoalPosition() {
        return goal;
    }

    public void setGoal(Position goal) {
        this.goal.setI(goal.getI());
        this.goal.setJ(goal.getJ());
    }

    public int getN() {
        return this.maze.length;
    }

    public int getM() {
        return this.maze[0].length;
    }

    public void print(){
        for(int i=0; i<this.getN(); i++){
            System.out.print("{ ");
            for(int j=0;j<this.getM(); j++) {
                if(i==start.getI() && j==start.getJ())
                    System.out.print("S ");
                else if(i==goal.getI() && j==goal.getJ())
                    System.out.print("E ");
                else if (this.getMaze()[i][j] == 1)
                    System.out.print("1 ");//System.out.print("\u2b1b");//
                else
                    System.out.print("0 ");//System.out.print("\u2B1C");//
            }
            System.out.println("}");
        }
    }
}
