package algorithms.maze3D;

/**
 * Maze3D is a 3D maze generated by one of the generators(factory design pattern)
 * field maze represents the walls and emptySpaces(1's and 0's respectively)
 * start is the maze's entrance point and goal is the maze's exit point
 */
public class Maze3D{
    private int[][][] map;
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
        this.map=maze;
        this.start=start;
        this.goal=goal;
    }

    //getters methods
    public int[][][] getMap() {
        return map;
    }

    public Position3D getStartPosition3D() {
        return start;
    }

    public Position3D getGoalPosition3D() {
        return goal;
    }

    public int getK(){
        return map.length;
    }

    public int getN(){
        return map[0].length;
    }

    public int getM(){
        return  map[0][0].length;
    }

    /**
     * @param goal 3D position indexing what position we want to declare as goal
     * @throws Exception if the position is invalid.
     */
    public void setGoalPosition3D(Position3D goal) throws Exception{
        //update goal position of maze to goal
        if(goal.getDepthIndex()<0 || goal.getRowIndex()<0 || goal.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.goal = goal;
    }

    /**
     * @param start 3D position indexing what position we want to declare as start
     * @throws Exception if the position is invalid.
     */
    public void setStartPosition3D(Position3D start) throws Exception{
        //update start position of maze to start
        if(start.getDepthIndex()<0 || start.getRowIndex()<0 || start.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes\n");
        }
        this.start = start;
    }

    /**
     * @param pos the 3D position we want to adjust in the maze
     * @param num the value we want to assign, 0/1.
     * @throws Exception if num was not 0 or 1.
     */
    public void setPlaceInMaze3D(Position3D pos, int num) throws Exception{
        //update value of specific position pos to num
        if(num!=0 && num!=1){
            throw new Exception("Maze3D can contain only zeroes or ones\n");
        }
        map[pos.getDepthIndex()][pos.getRowIndex()][pos.getColumnIndex()]=num;
    }

    /**
     * prints maze in requested format.
     * start position as S, goal position as E.
     * empty space as 0, wall as 1.
     */
    public void print(){
        //prints maze in requested format.
        System.out.println("{");
        for (int k=0; k<this.getK();k++) {
            for (int i = 0; i < this.getN(); i++) {
                System.out.print("{ ");
                for (int j = 0; j < this.getM(); j++) {
                    if (i == start.getRowIndex() && j == start.getColumnIndex() && k == start.getDepthIndex())
                        System.out.print("S ");
                    else if (i == goal.getRowIndex() && j == goal.getColumnIndex() && k == goal.getDepthIndex())
                        System.out.print("E ");
                    else if (this.getMap()[k][i][j] == 1)
                        System.out.print("1 ");//System.out.print("\u2b1b");//
                    else
                        System.out.print("0 ");//System.out.print("\u2B1C");//
                }
                System.out.println("}");
            }
            if(k!=this.getK()-1) {
                for (int j = 0; j < getM()*2+3; j++) {
                    if (j == getM() * 2 + 3 - 1) { System.out.println("-"); }
                    else { System.out.print("-"); }
                }
            }
        }
        System.out.println("}");
    }
}
