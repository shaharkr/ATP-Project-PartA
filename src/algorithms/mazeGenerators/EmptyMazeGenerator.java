package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    public EmptyMazeGenerator() {
    }

    @Override
    public Maze generate(int n, int m) {
        Maze maze = new Maze(n,m);
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Position pos = new Position(i,j);
                maze.setPlaceInMaze(pos,0);
            }
        }
        return maze;
    }
}
