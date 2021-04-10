package algorithms.search;

/**
 * ISearchingAlgorithm is an interface implemented by classes that represent
 * searching algorithm.
 */
public interface ISearchingAlgorithm {
    /**
     * @param searchable a problem which implements the searchable interface
     * @return Solution to the problem inputted.
     * @throws Exception if searchable problem is null-cannot solve it
     */
    Solution solve(ISearchable searchable) throws Exception;

    //getter.
    String getName();

    /**
     * @return number of nodes the algorithm traverses during its solve function
     */
    int getNumberOfNodesEvaluated();

    /**
     * @param domain a problem which implements the searchable interface
     * @return the amount of time in milliseconds the algorithm that implements this interface been running.
     * @throws Exception if searchable problem is null-cannot solve it
     */
    long measureAlgorithmTimeMillis(ISearchable domain) throws Exception;
}
