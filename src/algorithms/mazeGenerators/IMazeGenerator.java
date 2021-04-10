package algorithms.mazeGenerators;

/**
 * IMazeGenerator interface is a factory of the object Maze. every generator which implements this interface
 * must implement generate and measureAlgorithmTimeMillis.
 */
public interface IMazeGenerator {
    /**
     * @param n amount of rows in maze created
     * @param m amount of columns in maze created
     * @return Maze generated via an algorithm that inherits IMazeGenerator
     * @throws Exception if n or m are not positive integers.
     */
    Maze generate(int n, int m) throws Exception;
    /**
     * @param n amount of rows in maze created
     * @param m amount of columns in maze created
     * @return amount of time it took to create maze of size nXm, in milliseconds.
     * @throws Exception if n or m are not positive integers.
     */
    long measureAlgorithmTimeMillis(int n, int m) throws Exception;
}
