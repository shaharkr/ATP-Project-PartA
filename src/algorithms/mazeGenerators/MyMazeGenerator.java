package algorithms.mazeGenerators;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int n, int m) {
        EmptyMazeGenerator emg = new EmptyMazeGenerator();
        Maze maze = emg.generate(n,m);
        this.divide(maze, n, m,0,0);
        return maze;
    }
    private Orientation howToCut(int n, int m){
        if (n<m)
            return Orientation.VERTICAL;
        else
            return Orientation.HORIZONTAL;
    }
    private void divide( Maze maze, int width, int height,int i, int j){
        if(height<2 && width<2) {
            if(height==1 && width==1){
                Position pos=new Position(i,j);
                maze.setPlaceInMaze(pos,1);
            }
            return;
        }
        if(howToCut(height,width)==Orientation.HORIZONTAL){
            int row = (int)(Math.random()*height)+i;
            for(int k=j ;k<width+j;k++){
                Position pos = new Position(row,k);
                maze.setPlaceInMaze(pos,1);
            }
            int gate = (int)(Math.random()*width)+j;
            Position pos = new Position(row,gate);
            maze.setPlaceInMaze(pos,0);
            divide(maze,width,row-i,i,j);
            divide(maze,width,height-row-1,row+1,j);
        }
        else{
            int col = (int)(Math.random()*width)+j;
            for(int k=i ;k<height+i;k++){
                Position pos = new Position(k,col);
                maze.setPlaceInMaze(pos,1);
            }
            int gate =(int)(Math.random()*height)+i;
            Position pos = new Position(gate,col);
            maze.setPlaceInMaze(pos,0);
            divide(maze,col-j,height,i,j);
            divide(maze,width-col-1,height,i,col+1);
        }
    }
}
