package algorithms.search;

/**
 * AState is an abstract state in a searchable problem.
 * it implements Comparable(compared by cost).
 * cameFrom is the state from which this has been reached.
 * cost is the price wh had to pay for reaching this.
 * state is a string representation of this.
 */
public abstract class AState implements Comparable<AState>{
    private AState cameFrom;
    private double cost;
    private String state;

    /**
     * @param cost the price wh had to pay for reaching this.
     * @param state a string representation of this.
     */
    public AState(double cost, String state) {
        this.cameFrom = null;
        this.cost = cost;
        this.state = state;
    }

    //getters methods
    public AState getCameFrom() {
        return cameFrom;
    }
    public double getCost() {
        return cost;
    }
    public String getState() {
        return state;
    }

    //setters methods
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    @Override
    public int compareTo(AState o) {
        return Double.compare(this.cost,o.cost);
    }
}
