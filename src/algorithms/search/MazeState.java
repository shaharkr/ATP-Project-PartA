package algorithms.search;

import algorithms.mazeGenerators.Position;
/**
 * MazeState is a class made for adaptation between AState and Position.
 */
public class MazeState extends AState{
    private Position position;

    /**
     * @param position 2D position we want to adapt to AState
     * @param cost the price we want the AState to cost
     * @param state name of the State.
     */
    public MazeState(Position position, double cost, String state){
        super(cost,state);
        this.position = position;
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
            MazeState mzo = (MazeState) o;
            return position.equals(mzo.position);
        }
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

    //getter method of the position.
    public Position getPosition() {
        return position;
    }

    //setter method of the position.
    public void setPosition(Position position) throws Exception {
        if(position.getRowIndex()<0 || position.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes/n");
        }
        this.position = position;
    }
}
