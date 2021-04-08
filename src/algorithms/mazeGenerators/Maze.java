package algorithms.mazeGenerators;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class Maze {

    private int[][] maze;
    private Position start;
    private Position goal;

    public Maze(Position start, Position goal, int[][] maze) throws Exception{
        if(start==null || goal==null || maze==null)throw new Exception("Illegal input for maze constructor");
        if(start.getI()<0 || start.getJ()<0 ||goal.getI()<0||goal.getJ()<0)
            throw new Exception("Illegal positions for maze");
        this.maze=maze;
        this.start=start;
        this.goal=goal;
    }

    /**
     * @param pos position in maze we want to change
     * @param num input to position pos we want to update
     * @throws Exception if pos is illegal position or num is illegal input
     */
    public void setPlaceInMaze(Position pos, int num) throws Exception {
        if(num!=0 && num!=1){
            throw new Exception("Maze can contain only zeroes or ones\n");
        }
        if(pos.getI()<0 || pos.getJ()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        maze[pos.getI()][pos.getJ()]=num;
    }

    public void setStart(Position start) throws Exception{
        //updates start position of maze
        if(start.getI()<0 || start.getJ()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.start.setI(start.getI());
        this.start.setJ(start.getJ());
    }

    public void setGoal(Position goal) throws Exception{
        //update goal position of maze
        if(goal.getI()<0 || goal.getJ()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.goal.setI(goal.getI());
        this.goal.setJ(goal.getJ());
    }

    public Position getStartPosition() { return start; }

    public Position getGoalPosition() {
        return goal;
    }

    public int getN() { return this.maze.length; }

    public int getM() { return this.maze[0].length; }

    public int[][] getMaze() {
        return maze;
    }

    public void print(){
        //prints the maze, whereas start position is printed S, goal position is printed E
        //1 means wall, and 0 means empty space.
        for(int i=0; i<this.getN(); i++){
            System.out.print("{ ");
            for(int j=0;j<this.getM(); j++) {
                if(i==start.getI() && j==start.getJ())
                    System.out.print("S ");
                else if(i==goal.getI() && j==goal.getJ())
                    System.out.print("E ");
                else if (this.getMaze()[i][j] == 1)
                    System.out.print("1 ");//System.out.print("\u2b1b ");
                else
                    System.out.print("0 ");//System.out.print("\u2B1C ");
            }
            System.out.println("}");
        }
    }
}
