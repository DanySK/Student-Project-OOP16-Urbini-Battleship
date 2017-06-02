package it.unibo.battleship.common;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2dImpl that = (Point2dImpl) o;

        return Objects.equal(this.x, that.x) &&
                Objects.equal(this.y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

    @Override
    public String toString() {
        return " x = " + x + ", y = " + y;
    }
}
