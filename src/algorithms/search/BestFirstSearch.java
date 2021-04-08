package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.*;

public class BestFirstSearch  extends BreadthFirstSearch{
    protected Map<String,Double> D;

    public BestFirstSearch() {
        super();
        name="BestFirstSearch";
        Q= new PriorityQueue<>(); //Best first search uses the BFS logic with a priority queue.
        D = new HashMap<>();
    }

    @Override
    public Solution solve(ISearchable searchable) throws Exception {
        if(searchable==null)throw new Exception("Cannot solve null, searchable can't be null\n");
        D.put(searchable.getStartState().getState(), 0.0);
        return super.solve(searchable);
    }

    @Override
    protected void passSuccessors(AState as, AState curr){
        if (!Q.contains(as) && !visited.containsKey(as.getState())) { //first time found-white
            as.setCost(D.get(curr.getState()) + as.getCost());
            as.setCameFrom(curr);
            Q.add(as);
            D.put(as.getState(),as.getCost());
        }
        else if (visited.containsKey(as.getState())) //in visited-black
            return;
        else{ //in Q-grey
            if(D.get(as.getState())> D.get(curr.getState()) + as.getCost()){
                Q.remove(as);
                as.setCost(D.get(curr.getState()) + as.getCost());
                as.setCameFrom(curr);
                Q.add(as);
                D.put(as.getState(),as.getCost());
            }
        }
    }

}
