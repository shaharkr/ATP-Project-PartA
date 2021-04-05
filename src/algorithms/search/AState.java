package algorithms.search;

public abstract class AState implements Comparable<AState>{
    private AState cameFrom;
    private double cost;
    private String state;

    public AState(double cost, String state) {
        this.cameFrom = null;
        this.cost = cost;
        this.state = state;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public String getState() {
        return state;
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
