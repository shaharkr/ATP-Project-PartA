package algorithms.maze3D;

import java.util.Objects;

/**
 * Position 3D is an object representing a 3 coordinates.
 */
public class Position3D {
    private int i;
    private int j;
    private int k;

    /**
     * @param k the depth of the position
     * @param i the row of the position
     * @param j the column of the position
     */
    public Position3D(int k,int i, int j) {
        this.i = i;
        this.j = j;
        this.k=k;
    }

    //getters methods
    public int getDepthIndex() {return k;}

    public int getRowIndex() {return i;}

    public int getColumnIndex() {return j;}

    //setters methods
    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setK(int k) {
        this.k = k;
    }

    @Override
    /**
     * @return the format "{<depth>,<row>,<column>}"
     */
    public String toString() {
        return
                "{" + k +
                "," + i +
                "," + j +
                '}';
    }

    @Override
    /**
     * @param o a certain Object
     * @return true if o is a Position3D with the same depth,row,column as this. Otherwise false.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D that = (Position3D) o;
        return i == that.i && j == that.j && k == that.k;
    }

    @Override
    /**
     * @return the Java hashcode on multiple ints.
     */
    public int hashCode() {
        return Objects.hash(i, j, k);
    }
}
