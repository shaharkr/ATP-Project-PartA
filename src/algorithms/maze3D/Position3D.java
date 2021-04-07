package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

import java.util.Objects;

public class Position3D {
    private int i;
    private int j;
    private int k;

    public Position3D(int k,int i, int j) {
        this.i = i;
        this.j = j;
        this.k=k;
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

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return
                "{" + i +
                "," + j +
                "," + k +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D that = (Position3D) o;
        return i == that.i && j == that.j && k == that.k;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, k);
    }
}
