package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D{
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception {
        if(depth<0 || row<0 || column<0){
            throw new Exception("Invalid inputs: depth, row and column need to be positive integers\n");
        }
        long s,f;
        s=System.currentTimeMillis();
        this.generate(depth, row, column);
        f=System.currentTimeMillis();
        return f-s;
    }

}
