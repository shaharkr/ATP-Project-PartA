package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    Queue<AState> Q;
    Map<String,AState> visited;

    public BreadthFirstSearch() {
        super("BFS");
        Q = new LinkedList<>();
        visited = new HashMap<>();////////////
    }

    @Override
    public Solution solve(ISearchable searchable) {
        AState start = searchable.getStartState();
        AState end = searchable.getGoalState();
        Q.add(start);
        AState curr = start;
        while(!Q.isEmpty() && !curr.equals(end)){
            curr = Q.poll();
            visited.put(curr.getState(), curr);
            ArrayList<AState> lst = searchable.getAllSuccessors(curr);
            for (AState as:
                 lst) {
                if(!visited.containsKey(as.getState()) && !Q.contains(as)){
                    Q.add(as);
                    as.setCameFrom(curr);
                }
            }
        }
        if(curr.equals(end))
            return new Solution(curr);
        return null;
    }
}
