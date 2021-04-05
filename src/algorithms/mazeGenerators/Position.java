package algorithms.mazeGenerators;

import algorithms.search.MazeState;

public class Position {
    private int i;
    private int j;

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "{" +
                 i +
                "," + j +
                '}';
    }

    public boolean equals(Object o) {
        if (this.getClass() != o.getClass())
            return false;
        else{
            Position p = (Position) o;
            return (this.getI()==p.getI() && this.getJ()==p.getJ());
        }
    }
}
