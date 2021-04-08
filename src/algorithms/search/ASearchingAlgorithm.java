package algorithms.search;

import java.util.ArrayList;
import java.util.Map;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int numberOfNodesEvaluated=0;
    protected String name;
    protected Map<String,AState> visited;

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long measureAlgorithmTimeMillis(ISearchable domain) throws Exception{
        long s,f;
        s=System.currentTimeMillis();
        Solution sol=this.solve(domain);
        f=System.currentTimeMillis();
        return f-s;
    }

    //Update the amount of nodes the algorithm traversed.
    public void setNumberOfNodesEvaluated(int numberOfNodesEvaluated) throws Exception {
        if(numberOfNodesEvaluated<0)
            throw new Exception("Number of nodes evaluated must be positive\n");
        this.numberOfNodesEvaluated = numberOfNodesEvaluated;
    }
}
