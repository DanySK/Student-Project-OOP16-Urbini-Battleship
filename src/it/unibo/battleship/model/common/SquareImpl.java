package it.unibo.battleship.model.common;

public class SquareImpl implements Square {

    private final Point2d currentPoint;
    private State state;
    
    public SquareImpl(final Point2d currentPoint, final State state) {
        this.currentPoint = currentPoint;
        this.state = state;
    }
    @Override
    public Point2d getCurrentPoint() {
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
