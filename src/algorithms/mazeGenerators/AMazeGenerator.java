package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public AMazeGenerator() {
    }

    @Override
    public long measureAlgorithmTimeMillis(int n, int m) {
        long s,f;
        s=System.currentTimeMillis();
        this.generate(n,m);
        f=System.currentTimeMillis();
        return f-s;
    }
}
