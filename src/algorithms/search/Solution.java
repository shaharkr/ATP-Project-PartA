package algorithms.search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> sol;
    public Solution(AState as) {
        sol= new ArrayList<>();
        createPath(as);
    }

    /**
     * @param as state with updated cameFrom
     * runs over the cameFrom's until reaching null, aka, the start of the searchable problem.
     */
    private void createPath(AState as){
        AState curr = as;
        while (curr!=null){
            this.sol.add(0,curr);
            curr= curr.getCameFrom();
        }
    }

    public ArrayList<AState> getSolutionPath(){
        return sol;
    }

}
