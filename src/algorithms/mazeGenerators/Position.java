package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * Position is an object representing a 2 coordinates.
 */
public class Position implements Serializable {
    private int i;
    private int j;

    /**
     * @param i the row of the position
     * @param j the column of the position
     */
    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    //getters methods
    public int getRowIndex() {
        return i;
    }
    public int getColumnIndex() {
        return j;
    }

    //setters methods
    public void setI(int i) {
        this.i = i;
    }
    public void setJ(int j) {
        this.j = j;
    }

    @Override
    /**
     * @return String in the format "{<row>,<column>}"
     */
    public String toString() {
        return "{" +
                 i +
                "," + j +
                '}';
    }

    @Override
    /**
     * @param o a certain Object.
     * @return true if o is of type position having the same row and column as this.
     */
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass())
            return false;
        else{
            Position p = (Position) o;
            return (this.getRowIndex()==p.getRowIndex() && this.getColumnIndex()==p.getColumnIndex());
        }
    }
}
