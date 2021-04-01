package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    private int numberOfVisitedNodes;
    private String name;

    public ASearchingAlgorithm(String name) {
        this.name = name;
        this.numberOfVisitedNodes=0;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfVisitedNodes;
    }

    public void setNumberOfVisitedNodes(int numberOfVisitedNodes) {
        this.numberOfVisitedNodes = numberOfVisitedNodes;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
