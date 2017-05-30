package it.unibo.battleship.common;

/**
 * Implementation of a 2 dimension point
 * {@see Point2d}
 * @author fabio
 *
 */
public class Point2dImpl implements Point2d {

    private final int x;
    private final int y;

    public Point2dImpl (final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

}
