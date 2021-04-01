package algorithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator{
    public SimpleMazeGenerator() {
    }

    @Override

    public Maze generate(int n, int m) {
        int[][] map = new int[n][m];

        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                Position pos = new Position(i, j);
                double temp = Math.random();
                if (temp < 0.35)
                    map[i][j]=0;
                else
                    map[i][j]=1;
            }
        }
        int randS = (int)(Math.random()*(n-1));
        int randG = (int)(Math.random()*(n-1));
        Position start=new Position(randS,0);
        Position goal=new Position(randG,m-1);
        Maze maze = new Maze(start,goal,map);
        Position curr= new Position(maze.getStartPosition().getI(),maze.getStartPosition().getJ()); //check if it by value or reference
        /*m-1 randoms moves*/
        for (int k = 1; k < m; k++) {
            double num = Math.random();
            if(num<0.125 && curr.getJ()<m-1) { //right
                curr.setJ(curr.getJ()+1);
            }

            else if(num<0.375 && 0<curr.getI()) {//up
                curr.setI(curr.getI()-1);
            }

            else if(num<0.625 && 0<curr.getJ()) {//left
                curr.setJ(curr.getJ()-1);
            }

            else if(num<0.875 && curr.getI()<n-1) {//down
                curr.setI(curr.getI()+1);
            }
            /*else if(num<0.25 && 0<curr.getI() && curr.getJ()<m-1) {//right up
                curr.setI(curr.getI()-1);
                curr.setJ(curr.getJ()+1);
            }
            else if(num<0.5 && 0<curr.getI() && 0<curr.getJ()) {//left up
                curr.setI(curr.getI()-1);
                curr.setJ(curr.getJ()-1);
            }
            else if(num<0.75 && curr.getI()<n-1 && 0<curr.getJ()) {//left down
                curr.setI(curr.getI()+1);
                curr.setJ(curr.getJ()-1);
            }
            else if( curr.getI()<n-1 && curr.getJ()<m-1) {//right down
                curr.setI(curr.getI()+1);
                curr.setJ(curr.getJ()+1);
            }*/
            maze.setPlaceInMaze(curr, 0);
        }
        /*create path to goal*/
        while(curr.getI()!=maze.getGoalPosition().getI() || curr.getJ()!=maze.getGoalPosition().getJ()){
            if(curr.getI()<maze.getGoalPosition().getI()){
                curr.setI(curr.getI()+1);
                maze.setPlaceInMaze(curr, 0);
            }
            if(curr.getI()>maze.getGoalPosition().getI()){
                curr.setI(curr.getI()-1);
                maze.setPlaceInMaze(curr, 0);
            }
            if(curr.getJ()<maze.getGoalPosition().getJ()){
                curr.setJ(curr.getJ()+1);
                maze.setPlaceInMaze(curr, 0);
            }
            if(curr.getJ()>maze.getGoalPosition().getJ()){
                curr.setJ(curr.getJ()-1);
                maze.setPlaceInMaze(curr, 0);
            }
        }
        maze.setPlaceInMaze(maze.getStartPosition(),0);
        maze.setPlaceInMaze(maze.getGoalPosition(),0);
        return maze;
    }
}
