package it.unibo.battleship.model.common;

public class SquareImpl implements Square {

    private final PointImpl currentPoint;
    private State state;
    
    public SquareImpl(final PointImpl currentPoint, final State state) {
        this.currentPoint = currentPoint;
        this.state = state;
    }
    @Override
    public PointImpl getCurrentPoint() {
        return this.currentPoint;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public boolean setState(final State state) {
        this.state = state;
        return true;
    }

}
