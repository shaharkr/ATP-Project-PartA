package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;

/**
 * BreadthFirstSearch class extends the abstract ASearchingAlgorithm,
 * implementing solve function using the BFS algorithm.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> Q;

    /**
     * constructor, updates name to algorithm name.
     * uses a queue, and a hash map for already-visited nodes.
     */
    public BreadthFirstSearch() {
        name="BreadthFirstSearch";
        Q = new LinkedList<>(); //Q is the queue used on BFS search
        visited = new HashMap<>(); //visited contains the nodes traversed during the visit.
    }

    /**
     * @param searchable a problem that can be searched via BFS.
     * @return solution containing the nodes that the BFS found, or null if
     * no solution found
     * @throws Exception if getAllSuccessors receives null.
     */
    @Override
    public Solution solve(ISearchable searchable) throws Exception{
        super.solve(searchable);
        AState curr = searchable.getStartState();
        Q.add(curr);
        while(!Q.isEmpty() ){
            curr = Q.poll();
            visited.put(curr.getState(), curr);
            try{setNumberOfNodesEvaluated(this.getNumberOfNodesEvaluated()+1);}
            catch (Exception e) {e.getMessage();}
            if(curr.equals(searchable.getGoalState())){
                break;
            }
            ArrayList<AState> lst = searchable.getAllSuccessors(curr);
            for (AState as: lst) {
                this.passSuccessors(as,curr);
            }
        }
        return new Solution(curr);
    }

    /**
     * @param as state that curr can reach
     * @param curr current state in searching pattern
     * checking if as has not been visited before and is out of the queue
     */
    protected void passSuccessors(AState as, AState curr){
        if(!visited.containsKey(as.getState()) && !Q.contains(as)){
            Q.add(as);
            as.setCameFrom(curr);
        }
    }
}
