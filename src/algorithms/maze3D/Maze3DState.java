package algorithms.maze3D;

import algorithms.search.AState;

/**
 * Maze3DState is a class made for adaptation between AState and 3D position.
 */
public class Maze3DState extends AState {
    private Position3D position;

    /**
     * @param pos 3D position we want to adapt to AState
     * @param cost the price we want the AState to cost
     * @param state name of the State.
     */
    public Maze3DState( Position3D pos, double cost, String state) {
        super(cost, state);
        this.position=pos;
    }

    //getter method of the position.
    public Position3D getPosition() {
        return position;
    }

    //setter method of the position.
    public void setPosition(Position3D position) throws Exception {
        if(position.getDepthIndex()<0 || position.getRowIndex()<0 || position.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes/n");
        }
        this.position = position;
    }

    /**
     * @return hashcode of the string state.
     */
    @Override
    public int hashCode() {
        return super.getState()!=null ? super.getState().hashCode() : 0;
    }

    /**
     * @return position's string.
     */
    @Override
    public String toString() {
        return position.toString();
    }

    /**
     * @param o a certain object
     * @return true if o is Maze3DState and the positions are equal,
     * otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass())
            return false;
        else{
            Maze3DState mzo = (Maze3DState) o;
            return this.position.equals(mzo.position);
        }
    }
}
