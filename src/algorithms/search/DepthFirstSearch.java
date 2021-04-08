package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {
    protected Stack<AState> s;

    public DepthFirstSearch() {
        name="DepthFirstSearch";
        visited = new HashMap<>();
        s = new Stack<>(); //Stack is used in iterative version if dfs visit.
    }

    @Override
    public Solution solve(ISearchable searchable) throws Exception{
        if(searchable==null)throw new Exception("Cannot solve null, searchable can't be null\n");
        AState start = searchable.getStartState();
        AState end = searchable.getGoalState();
        AState res = dfsVisitIter( searchable, start, end);
        return new Solution(res);
    }

    /**
     * @param searchable problem that implements the searchable interface
     * @param curr current node the dfs is visiting. first current is the problem's starting position.
     * @param end destination node, meaning the searchable solution.
     * @return returns the problem's goal state if exists (else null), when every state's "cameFrom" is updated.
     * @throws Exception if get all successors is activated on null.
     */
    private AState dfsVisitIter(ISearchable searchable, AState curr,AState end) throws Exception{
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
