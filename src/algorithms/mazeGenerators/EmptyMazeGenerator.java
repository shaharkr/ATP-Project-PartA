package algorithms.mazeGenerators;

/**
 * EmptyMazeGenerator is a basic 2D maze generator, creating an empty maze with start at [0,0].
 * it extends AMazeGenerator which implements measureTimeInMilliseconds.
 */
public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int n, int m) throws Exception{
        if(n<0 || m<0){
            throw new Exception("Invalid inputs: n and m need to be positive integers\n");
        }
        int[][] maze = new int[n][m];
        //initiate the maze with empty spaces(0's)
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maze[i][j]=0;
            }
        }
        //set start as upper left corner and goal as lower right corner
        Position start=new Position(0,0);
        Position goal=new Position(n-1,m-1);
        return new Maze(start,goal,maze);
    }
}
