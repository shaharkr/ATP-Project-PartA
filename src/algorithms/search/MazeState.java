package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position position;

    public MazeState(Position position, double cost, String state){
        super(cost,state);
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass())
            return false;
        else{
            MazeState mzo = (MazeState) o;
            return position.equals(mzo.position);
        }
    }

    @Override
    public int hashCode() {
        return super.getState()!=null ? super.getState().hashCode() : 0;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) throws Exception {
        if(position.getI()<0 || position.getJ()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes/n");
        }
        this.position = position;
    }
}
