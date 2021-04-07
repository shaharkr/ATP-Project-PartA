package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D{
    private int[][][] maze;
    private Position3D start;
    private Position3D goal;

    public Maze3D(Position3D start, Position3D goal, int[][][] maze) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
    }

    public int[][][] getMaze() {
        return maze;
    }

    public void setMaze(int[][][] maze) {
        this.maze = maze;
    }

    public Position3D getStartPosition3D() {
        return start;
    }

    public void setStartPosition3D(Position3D start) {
        this.start = start;
    }

    public Position3D getGoalPosition3D() {
        return goal;
    }

    public void setGoalPosition3D(Position3D goal) {
        this.goal = goal;
    }

    public int getK(){
        return maze.length;
    }

    public int getN(){
        return maze[0].length;
    }

    public int getM(){
        return  maze[0][0].length;
    }

    public void setPlaceInMaze3D(Position3D pos, int num) {
        maze[pos.getK()][pos.getI()][pos.getJ()]=num;
    }

    public void print(){
        System.out.println("{");
        for (int k=0; k<this.getK();k++) {
            for (int i = 0; i < this.getN(); i++) {
                System.out.print("{ ");
                for (int j = 0; j < this.getM(); j++) {
                    if (i == start.getI() && j == start.getJ() && k == start.getK())
                        System.out.print("S ");
                    else if (i == goal.getI() && j == goal.getJ() && k == start.getK())
                        System.out.print("E ");
                    else if (this.getMaze()[k][i][j] == 1)
                        System.out.print("1 ");//System.out.print("\u2b1b");//
                    else
                        System.out.print("0 ");//System.out.print("\u2B1C");//
                }
                System.out.println("}");
            }
            if(k!=this.getK()-1) {
                for (int j = 0; j < getM(); j++) {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        System.out.println("}");
    }
}
