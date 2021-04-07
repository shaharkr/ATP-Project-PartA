package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;

public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState as);
    ArrayList<AState> getAllAStates();
}
