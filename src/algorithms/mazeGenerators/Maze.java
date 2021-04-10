package algorithms.mazeGenerators;

/**
 * this class is representing a 2D maze.
 * maze is a 2D matrix where 0 is an empty space and 1 is a wall in the maze.
 * start is a position object representing the entrance point to the maze.
 * goal is a position object representing the exit point of the maze.
 */
public class Maze {
    private int[][] maze;
    private Position start;
    private Position goal;

    /**
     * creates maze when all fields are inputted.
     * @throws Exception if the fields are illegal for maze.
     */
    public Maze(Position start, Position goal, int[][] maze) throws Exception{
        if(start==null || goal==null || maze==null)throw new Exception("Illegal input for maze constructor");
        if(start.getRowIndex()<0 || start.getColumnIndex()<0 ||goal.getRowIndex()<0||goal.getColumnIndex()<0)
            throw new Exception("Illegal positions for maze");
        this.maze=maze;
        this.start=start;
        this.goal=goal;
    }

    //getters methods
    public Position getStartPosition() { return start; }

    public Position getGoalPosition() {
        return goal;
    }

    public int getN() { return this.maze.length; }

    public int getM() { return this.maze[0].length; }

    public int[][] getMaze() {
        return maze;
    }

    //setters methods
    public void setStart(Position start) throws Exception{
        //updates start position of maze
        if(start.getRowIndex()<0 || start.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.start.setI(start.getRowIndex());
        this.start.setJ(start.getColumnIndex());
    }

    public void setGoal(Position goal) throws Exception{
        //update goal position of maze
        if(goal.getRowIndex()<0 || goal.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.goal.setI(goal.getRowIndex());
        this.goal.setJ(goal.getColumnIndex());
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
        if(pos.getRowIndex()<0 || pos.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        maze[pos.getRowIndex()][pos.getColumnIndex()]=num;
    }

    /**
     * prints maze in requested format.
     * start position as S, goal position as E.
     * empty space as 0, wall as 1.
     */
    public void print(){
        //prints the maze, whereas start position is printed S, goal position is printed E
        //1 means wall, and 0 means empty space.
        for(int i=0; i<this.getN(); i++){
            System.out.print("{ ");
            for(int j=0;j<this.getM(); j++) {
                if(i==start.getRowIndex() && j==start.getColumnIndex())
                    System.out.print("S ");
                else if(i==goal.getRowIndex() && j==goal.getColumnIndex())
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
