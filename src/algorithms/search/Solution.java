package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Solution is a class representing a series of states leading from
 * start position to a goal position.
 */
public class Solution implements Serializable {
    ArrayList<AState> sol;
    public Solution(AState as) {
        sol= new ArrayList<>();
        createPath(as);
    }

    public Solution(ArrayList<AState> sol) {
        this.sol = sol;
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

    //getter to the solution member.
    public ArrayList<AState> getSolutionPath(){
        return sol;
    }

}
