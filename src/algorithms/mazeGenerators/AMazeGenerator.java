package algorithms.mazeGenerators;

/**
 * AMazeGenerator is an abstract class implementing the maze generator interface.
 * it implements measureAlgorithmTime for its sub-classes.
 */
public abstract class AMazeGenerator implements IMazeGenerator {
    @Override
    //JavaDoc in IMazeGenerator
    public long measureAlgorithmTimeMillis(int n, int m) throws Exception{
        long s,f;
        s=System.currentTimeMillis();
        this.generate(n,m);
        f=System.currentTimeMillis();
        return f-s;
    }
}
