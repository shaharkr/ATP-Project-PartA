package algorithms.maze3D;
/**
 * AMaze3DGenerator is an abstract class, implementing Interface of 3D MazeGenerator.
 * its object is to be a super class of specific maze generators, implementing one
 * of their functionality.
 */
public abstract class AMaze3DGenerator implements IMaze3DGenerator{
    //JavaDoc in IMaze3DGenerator
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
