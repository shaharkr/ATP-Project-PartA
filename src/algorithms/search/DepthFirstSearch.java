package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {
    protected Map<String,AState> visited;
    protected Stack<AState> s;

    public DepthFirstSearch() {
        name="DepthFirstSearch";
        visited = new HashMap<>();
        s = new Stack<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        AState start = searchable.getStartState();
        AState end = searchable.getGoalState();
        AState res = dfsVisitIter( searchable, start, end);
        return new Solution(res);
    }

    private AState dfsVisitIter(ISearchable searchable, AState curr,AState end){
        s.push(curr);
        while(!s.isEmpty()){
            curr=s.pop();
            if(curr.equals(end))
                return curr;
            if (!visited.containsKey(curr.getState())){
                visited.put(curr.getState(),curr);
                setNumberOfNodesEvaluated(this.getNumberOfNodesEvaluated()+1);
                ArrayList<AState> lst = searchable.getAllSuccessors(curr);
                for (AState as: lst) {
                    as.setCameFrom(curr);
                    s.push(as);
                }
            }

        }
        return null;
    }










}
