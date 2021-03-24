package algorithms.mazeGenerators;

public class Maze {

    private int[][] maze;
    private Position start;
    private Position goal;
    private int n, m;

    public Maze(int n, int m) {
        this.n=n;
        this.m=m;
        maze = new int[n][m];
        this.start=new Position(0,0);
        this.goal=new Position(n-1,m-1);
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setPlaceInMaze(Position pos, int num) {
        maze[pos.getI()][pos.getJ()]=num;
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start.setI(start.getI());
        this.start.setJ(start.getJ());
    }

    public Position getGoal() {
        return goal;
    }

    public void setGoal(Position goal) {
        this.goal.setI(start.getI());
        this.goal.setJ(start.getJ());
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
}
