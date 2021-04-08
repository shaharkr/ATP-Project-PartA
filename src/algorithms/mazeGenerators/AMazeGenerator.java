package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {



    @Override
    public long measureAlgorithmTimeMillis(int n, int m) throws Exception{
        long s,f;
        s=System.currentTimeMillis();
        this.generate(n,m);
        f=System.currentTimeMillis();
        return f-s;
    }
}
