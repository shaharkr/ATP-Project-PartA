package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int n, int m);
    long measureAlgorithmTimeMillis(int n, int m);
}
