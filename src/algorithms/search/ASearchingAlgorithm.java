package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int numberOfNodesEvaluated=0;
    protected String name;

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfNodesEvaluated(int numberOfNodesEvaluated) {
        this.numberOfNodesEvaluated = numberOfNodesEvaluated;
    }

    public long measureAlgorithmTimeMillis(ISearchable domain){
        long s,f;
        s=System.currentTimeMillis();
        Solution sol=this.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", this.getName(), this.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = sol.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
        f=System.currentTimeMillis();
        return f-s;
    }
}
