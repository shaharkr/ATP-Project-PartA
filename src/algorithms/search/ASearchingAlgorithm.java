package algorithms.search;

import java.util.Map;
/**
 * ASearchingAlgorithm is an abstract class implementing the SearchingAlgorithm interface.
 * it implements getters, and measureAlgorithmTimeMillis for its sub-classes.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int numberOfNodesEvaluated=0;
    protected String name;
    protected Map<String,AState> visited;

    //getters methods
    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Solution solve(ISearchable searchable) throws Exception{
        if(searchable==null)throw new NullPointerException("Cannot solve null, searchable can't be null\n");
        return null;
    }

    @Override
    public long measureAlgorithmTimeMillis(ISearchable domain) throws Exception{
        long s,f;
        s=System.currentTimeMillis();
        Solution sol=this.solve(domain);
        f=System.currentTimeMillis();
        return f-s;
    }

    /**
     * @param numberOfNodesEvaluated new number of visited states.
     * @throws Exception if input is illegal number of visited states.
     */
    protected void setNumberOfNodesEvaluated(int numberOfNodesEvaluated) throws Exception {
        if(numberOfNodesEvaluated<0)
            throw new Exception("Number of nodes evaluated must be positive\n");
        this.numberOfNodesEvaluated = numberOfNodesEvaluated;
    }
}
