package algorithms.search;

import java.util.*;

/**
 * this class extends BFS algorithm by using a priority queue instead of regular queue.
 * the successor traversing is different.
 */
public class BestFirstSearch  extends BreadthFirstSearch{
    protected Map<String,Double> D;

    /**
     * constructor, calls super and changes name.
     * initiates Q to priority queue
     * uses a map to save the minimal total cost. D[u] = minimal total cost to u
     * found so far.
     */
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

    /**
     * @param as   state that curr can reach
     * @param curr current state in searching pattern
     * checks if node is has been seen before, if not, adding it to Q and D.
     * if yes, and the node was removed from Q, continues without action.
     * if yes, but node is still in Q, updates it's cost if relevant.
     */
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
