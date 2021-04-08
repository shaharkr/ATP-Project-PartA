package algorithms.maze3D;

public interface IMazeGenerator3D {
    /**
     * @param depth = depth of requested maze
     * @param row = rows(n) of requested maze
     * @param column = columns(m) of requested maze
     * @return 3D maze generated.
     * @throws Exception if maze size is illegal.
     */
    Maze3D generate(int depth, int row, int column) throws Exception;
    /**
     * @param depth = depth of maze we want to generate
     * @param row = rows(n) of maze we want to generate
     * @param column = columns(m) of maze we want to generate
     * @return amount of time in milliseconds it took to generate the maze
     * @throws Exception if size of wanted maze is illegal.
     */
    long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception;
}
