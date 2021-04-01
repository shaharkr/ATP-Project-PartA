package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    public EmptyMazeGenerator() {
    }

    @Override
    public Maze generate(int n, int m) {
        int[][] maze = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                maze[i][j]=0;
            }
        }
        Position start=new Position(0,0);
        Position goal=new Position(n-1,m-1);
        return new Maze(start,goal,maze);
    }
}
