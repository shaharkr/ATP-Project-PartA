package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Maze3D{
    private int[][][] maze;
    private Position3D start;
    private Position3D goal;

    /**
     * @param start = start position of the maze
     * @param goal = end position of the maze
     * @param maze = 3D array of int representing maze walls via 1's and clear paths via 0's.
     * @throws Exception if start or goal are illegal.
     */
    public Maze3D(Position3D start, Position3D goal, int[][][] maze) throws Exception{
        if(start==null || goal==null || maze==null)throw new Exception("Illegal input for maze constructor");
        if(start.getDepthIndex()<0 || start.getRowIndex()<0||start.getColumnIndex()<0 || goal.getDepthIndex()<0||goal.getRowIndex()<0||goal.getColumnIndex()<0)
            throw new Exception("Illegal positions for maze");
        this.maze=maze;
        this.start=start;
        this.goal=goal;
    }

    public void setPlaceInMaze3D(Position3D pos, int num) throws Exception{
        //update value of specific position pos to num
        if(num!=0 && num!=1){
            throw new Exception("Maze3D can contain only zeroes or ones\n");
        }
        maze[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()]=num;
    }

    public void setGoalPosition3D(Position3D goal) throws Exception{
        //update goal position of maze to goal
        if(goal.getDepthIndex()<0 || goal.getRowIndex()<0 || goal.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.goal = goal;
    }

    public void setStartPosition3D(Position3D start) throws Exception{
        //update start position of maze to start
        if(start.getDepthIndex()<0 || start.getRowIndex()<0 || start.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.start = start;
    }

    public void setMaze(int[][][] maze) {this.maze = maze;}

    public int[][][] getMaze() {
        return maze;
    }

    public Position3D getStartPosition3D() {
        return start;
    }

    public Position3D getGoalPosition3D() {
        return goal;
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

    public void print(){
        //prints maze in requested format.
        System.out.println(this.getStartPosition3D().toString());
        System.out.println(this.getGoalPosition3D().toString());
        System.out.println("{");
        for (int k=0; k<this.getK();k++) {
            for (int i = 0; i < this.getN(); i++) {
                System.out.print("{ ");
                for (int j = 0; j < this.getM(); j++) {
                    if (i == start.getRowIndex() && j == start.getColumnIndex() && k == start.getDepthIndex())
                        System.out.print("S ");
                    else if (i == goal.getRowIndex() && j == goal.getColumnIndex() && k == goal.getDepthIndex())
                        System.out.print("E ");
                    else if (this.getMaze()[k][i][j] == 1)
                        System.out.print("1 ");//System.out.print("\u2b1b");//
                    else
                        System.out.print("0 ");//System.out.print("\u2B1C");//
                }
                System.out.println("}");
            }
            if(k!=this.getK()-1) {
                for (int j = 0; j < getM()*2+3; j++) {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        System.out.println("}");
    }
}
