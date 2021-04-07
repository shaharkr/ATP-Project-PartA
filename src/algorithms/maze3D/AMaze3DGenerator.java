package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D{

    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long s,f;
        s=System.currentTimeMillis();
        this.generate(depth, row, column);
        f=System.currentTimeMillis();
        return f-s;
    }
}
