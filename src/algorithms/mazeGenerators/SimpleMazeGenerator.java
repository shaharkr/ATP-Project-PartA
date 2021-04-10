package algorithms.mazeGenerators;

/**
 * SimpleMazeGenerator is a specific maze generator. it extends AMazeGenerator so measureTimeInMillis
 * is already implemented. It implements generate in a simple-non algorithmic way.
 */
public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    /**
     * We randomize the maze, having most of it as walls.
     * Then, we make m-1 steps where every step breaks wall, randomly.
     * Finally, we make more steps up until reaching the goal.
     * @param n amount of rows in maze created
     * @param m amount of columns in maze created
     * @return a 2D maze created by m-1 random steps, and then straight way to goal.
     * @throws Exception if maze size is illegal.
     */
    public Maze generate(int n, int m) throws Exception{
        if(n<0 || m<0){
            throw new Exception("Invalid inputs: n and m need to be positive integers\n");
        }
        int[][] map = new int[n][m];
        //randomize maze, most of the cells should be 1(walls).
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
        if(randG==randS && m==1){
            if(randS>0){randG--;}
            else{randG++;}
        }
        Position start=new Position(randS,0);
        Position goal=new Position(randG,m-1);
        Maze maze = new Maze(start,goal,map);
        Position curr= new Position(maze.getStartPosition().getRowIndex(),maze.getStartPosition().getColumnIndex());
        /*m-1 randoms moves*/
        this.randomMoves(maze,curr);
        /*create path to goal*/
        this.createSimplePath(maze,curr);
        maze.setPlaceInMaze(maze.getStartPosition(),0);
        maze.setPlaceInMaze(maze.getGoalPosition(),0);
        return maze;
    }

    /**
     * @param maze the maze that we want to create using the simple path algorithm
     * @param curr the current position after we randomized steps
     * this method is in charge of creating a path from current position curr to goal position
     */
    private void createSimplePath(Maze maze, Position curr){
        while(curr.getRowIndex()!=maze.getGoalPosition().getRowIndex() || curr.getColumnIndex()!=maze.getGoalPosition().getColumnIndex()){
            if(curr.getRowIndex()<maze.getGoalPosition().getRowIndex()) {
                curr.setI(curr.getRowIndex() + 1);
                try { maze.setPlaceInMaze(curr, 0); }
                catch (Exception e) { System.out.println(e.getMessage()); }
            }
            if(curr.getRowIndex()>maze.getGoalPosition().getRowIndex()){
                curr.setI(curr.getRowIndex()-1);
                try{maze.setPlaceInMaze(curr, 0);}
                catch (Exception e) { System.out.println(e.getMessage()); }
            }
            if(curr.getColumnIndex()<maze.getGoalPosition().getColumnIndex()){
                curr.setJ(curr.getColumnIndex()+1);
                try{maze.setPlaceInMaze(curr, 0);}
                catch (Exception e) { System.out.println(e.getMessage()); }
            }
            if(curr.getColumnIndex()>maze.getGoalPosition().getColumnIndex()){
                curr.setJ(curr.getColumnIndex()-1);
                try{maze.setPlaceInMaze(curr, 0);}
                catch (Exception e) { System.out.println(e.getMessage()); }
            }
        }
    }


    /**
     * @param maze the maze we want to create via simple path
     * @param curr start position of the maze
     * this method will generate m-1 random moves, moving curr.
     */
    private void randomMoves(Maze maze, Position curr){
        int n= maze.getN(),m=maze.getM();
        for (int k = 1; k < m; k++) {
            double num = Math.random();
            if(num<0.125 && curr.getColumnIndex()<m-1) { //right
                curr.setJ(curr.getColumnIndex()+1);
            }

            else if(num<0.375 && 0<curr.getRowIndex()) {//up
                curr.setI(curr.getRowIndex()-1);
            }

            else if(num<0.625 && 0<curr.getColumnIndex()) {//left
                curr.setJ(curr.getColumnIndex()-1);
            }

            else if(num<0.875 && curr.getRowIndex()<n-1) {//down
                curr.setI(curr.getRowIndex()+1);
            }
            try{maze.setPlaceInMaze(curr, 0);}
            catch (Exception e) { System.out.println(e.getMessage()); }
        }
    }
}
