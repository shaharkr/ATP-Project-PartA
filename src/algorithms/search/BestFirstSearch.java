package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.*;

public class BestFirstSearch  extends ASearchingAlgorithm{
    protected Queue<AState> Q;
    protected Map<String,AState> visited;
    protected Map<String,AState> D;

    public BestFirstSearch() {
        super();
        name="BestFirstSearch";
        Q= new PriorityQueue<>();
        visited = new HashMap<>();
        D = new HashMap<>();
    }

    public Solution solve(ISearchable searchable){
        ArrayList<AState> as_lst = searchable.getAllAStates();
        AState curr = searchable.getStartState();
        for (AState as: as_lst ) {
            if(as.equals(curr)){
                as.setCost(0);
                curr=as;
            }
            else{
                as.setCost(Double.POSITIVE_INFINITY);
            }
            as.setCameFrom(null);
            D.put(as.getState(),as);
        }
        Q.add(curr);
        while(!Q.isEmpty()){
            curr=Q.poll();
            visited.put(curr.getState(),curr);
            ArrayList<AState> successors = searchable.getAllSuccessors(curr);
            for (AState as: successors) {
                AState asD = D.get(as.getState());
                if(asD.getCost()> curr.getCost() + as.getCost()){
                    asD.setCost(curr.getCost() + as.getCost());
                    asD.setCameFrom(curr);
                }
            }
        }
        AState fin = D.get(searchable.getGoalState().getState());
        return new Solution(fin);
    }

    public Solution solve1(ISearchable searchable) {
        Queue<AState> goal_op=new PriorityQueue<>();
        AState start = searchable.getStartState();
        AState end = searchable.getGoalState();
        Q.add(start);
        AState curr = start;
        while(!Q.isEmpty()){
            curr = Q.poll();
            visited.put(curr.getState(), curr);
            setNumberOfNodesEvaluated(this.getNumberOfNodesEvaluated()+1);
            ArrayList<AState> lst = searchable.getAllSuccessors(curr);
            for (AState as: lst) {
                if(!visited.containsKey(as.getState())){
                    if(as.equals(end)){
                        goal_op.add(as);
                    }
                    Q.add(as);
                    as.setCameFrom(curr);
                }
            }
        }
        AState min_cost = goal_op.poll();
        return new Solution(min_cost);
    }

}
