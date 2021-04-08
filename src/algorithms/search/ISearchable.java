package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();

    /**
     * @param as a certain state in the searchable problem.
     * @return list of states that can be reached from as, updated with their cost.
     * @throws Exception if as is null- cannot find its successors.
     */
    ArrayList<AState> getAllSuccessors(AState as) throws Exception;
}
