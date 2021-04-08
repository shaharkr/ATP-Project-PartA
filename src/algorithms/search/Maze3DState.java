package algorithms.search;

import algorithms.maze3D.Position3D;
import algorithms.mazeGenerators.Position;

public class Maze3DState extends AState{
    private Position3D position;

    public Maze3DState( Position3D pos, double cost, String state) {
        super(cost, state);
        this.position=pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass())
            return false;
        else{
            Maze3DState mzo = (Maze3DState) o;
            return this.position.equals(mzo.position);
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

    public Position3D getPosition() {
        return position;
    }

    public void setPosition(Position3D position) throws Exception {
        if(position.getDepthIndex()<0 || position.getRowIndex()<0 || position.getColumnIndex()<0){
            throw new Exception("Invalid inputs: Position in Maze must have positives indexes/n");
        }
        this.position = position;
    }
}
