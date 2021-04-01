package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable searchable);
    String getName();
    int getNumberOfNodesEvaluated();
}
