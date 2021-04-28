package algorithms.mazeGenerators;

import java.util.Stack;

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
     * creates maze when all the details are given in a byte array.
     * the byte array is in the order: [rows, colums, row start position, col start positin, row goal position, col goal position, maze's 1's and 0's in order]
     * @throws Exception if the fields are illegal for maze.
     */
    public Maze(byte[] B) throws Exception{
        Position s, g;
        int[] det = new int[6];
        int index=0,i=0;
        while(index<24){
            det[i] = byteToInt(B[index],B[index+1],B[index+2],B[index+3]);
            index+=4;
            i++;
        }
        int rows=det[0],cols=det[1],s_i=det[2],s_j=det[3],g_i=det[4],g_j=det[5];
        this.start = new Position(s_i,s_j);
        this.goal = new Position(g_i,g_j);
        this.maze = new int[rows][cols];
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < cols; k++) {
                maze[j][k] = (int)B[index];
                index++;
            }
        }

    }

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

    /**
     * this method is created for compression reasons, it will take the maze and transform it into a byte array,
     * according to the format: [rows(4), columns(4),start position(8), goal position(8), every position in the maze array...]
     * @return the byte array described above.
     */
    public byte[] toByteArray(){
        int s_i=start.getRowIndex(),s_j=start.getColumnIndex(),g_i=goal.getRowIndex(), g_j=goal.getColumnIndex(), rows = maze.length, cols = maze[0].length;
        byte[] row_col = mergeArrays(intToByte(rows), intToByte(cols));

        byte[] start_pos = mergeArrays(intToByte(s_i), intToByte(s_j));
        byte[] goal_pos = mergeArrays(intToByte(g_i), intToByte(g_j));
        byte[] start_goal = mergeArrays(start_pos,goal_pos);

        byte[] deteails = mergeArrays(row_col,start_goal);

        byte[] maze_numbers = new byte[maze.length*maze[0].length];
        int i=0, index =0;
        for (int[] row: maze) {
            while(i<rows){
                maze_numbers[index] = (byte)row[i];
                i++;
                index++;
            }
            i=0;
        }
        return mergeArrays(deteails,maze_numbers);
    }

    /**
     * this method inputs an integer and returns a four byte array representing the integer.
     * @param num integer smaller than 1020
     * @return byte array representing integer.
     */
    private byte[] intToByte(int num){
        byte[] to_ret = new byte[4];
        int i=0;
        while(num>255){
            num=num-255;
            to_ret[i]=(byte)255;
            i++;
        }
        to_ret[i]=(byte)num;
        return to_ret;
    }

    /**
     * combine two arrays of bytes to one.
     * @param src1 first array
     * @param src2 second array
     * @return [first.., second..]
     */
    private byte[] mergeArrays(byte[] src1, byte[] src2){
        byte[] to_ret = new byte[src1.length+src2.length];
        System.arraycopy(src1, 0, to_ret, 0, src1.length);
        System.arraycopy(src2, 0, to_ret, src1.length, src2.length);
        return to_ret;
    }


    /**
     * convert byte number to int number. maximum int size is 1020.
     * @param a first byte
     * @param b second byte
     * @param c third byte
     * @param d fourth byte
     * @return integer represented by the four bytes.
     */
    private int byteToInt(byte a,byte b,byte c,byte d){
        int ai,bi,ci,di;
        if((int)a<0){ai = 256+(int)a; }
        else{ai=(int)a;}
        if((int)b<0){bi = 256+(int)b; }
        else{bi=(int)b;}
        if((int)c<0){ci = 256+(int)c; }
        else{ci=(int)c;}
        if((int)d<0){di = 256+(int)d; }
        else{di=(int)d;}
        return ai+bi+ci+di;
    }
}
