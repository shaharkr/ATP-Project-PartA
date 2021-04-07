package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.*;

public class BestFirstSearch  extends ASearchingAlgorithm{
    protected Queue<AState> Q;
    protected Map<String,AState> visited;
    protected Map<String,Double> D;

    public BestFirstSearch() {
        super();
        name="BestFirstSearch";
        Q= new PriorityQueue<>();
        visited = new HashMap<>();
        D = new HashMap<>();
    }

    public Solution solve(ISearchable searchable){
        AState curr = searchable.getStartState();
        curr.setCost(0);
        D.put(curr.getState(),0.0);
        Q.add(curr);
        while (!Q.isEmpty()) {
            curr = Q.poll();
            if(curr.equals(searchable.getGoalState())){
                break;
            }
            for (AState as : searchable.getAllSuccessors(curr)) {
                if (!Q.contains(as) && !visited.containsKey(as.getState())) { //first time found
                    as.setCost(D.get(curr.getState()) + as.getCost());
                    as.setCameFrom(curr);
                    Q.add(as);
                    D.put(as.getState(),as.getCost());
                }
                else if (!Q.contains(as)) //in visited
                    continue;
                else{ //in Q
                    if(D.get(as.getState())> D.get(curr.getState()) + as.getCost()){
                        Q.remove(as);
                        as.setCost(D.get(curr.getState()) + as.getCost());
                        as.setCameFrom(curr);
                        Q.add(as);
                        D.put(as.getState(),as.getCost());
                    }
                }

            }
            visited.put(curr.getState(),curr);
        }
        return new Solution(curr);
    }

/*
    public Solution solve2(ISearchable searchable){
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
            Q.add(as);
        }
        while(!Q.isEmpty()){
            curr=Q.poll();
            if(curr.equals(searchable.getGoalState())){
                break;
            }
            visited.put(curr.getState(),curr);
            ArrayList<AState> successors = searchable.getAllSuccessors(curr);
            for (AState as: successors) {
                if (!Q.contains(as))
                    continue;
                AState asD = D.get(as.getState());
                if(asD.getCost()> curr.getCost() + as.getCost()){
                    Q.remove(asD);
                    asD.setCost(curr.getCost() + as.getCost());
                    asD.setCameFrom(curr);
                    Q.add(asD);
                }
            }
        }
        //AState fin = visited.get(searchable.getGoalState().getState());
        return new Solution(curr);
    }

    public Solution solve1(ISearchable searchable) {
        Queue<AState> goal_op=new PriorityQueue<>();
        AState start = searchable.getStartState();
        AState end = searchable.getGoalState();
        Q.add(start);
        AState curr = start;
        while(!Q.isEmpty()){
            curr = Q.poll();
            if(curr.equals(searchable.getGoalState())){
                break;
            }
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
*/


}
