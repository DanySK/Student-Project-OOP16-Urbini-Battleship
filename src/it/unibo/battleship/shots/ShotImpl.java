package it.unibo.battleship.shots;

import com.google.common.base.Objects;
import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.common.Ruleset;

/**
 * Implementation of a Shot
 */
public final class ShotImpl implements Shot {
    private final Point2d point;

    private ShotImpl(final Point2d p) {
        this.point = p;
    }

    public static ShotImpl createShot(Point2d p) {
        if (Ruleset.isPointWithinLimits(p)) {
            return new ShotImpl(p);
        } else throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS);
    }
    @Override
    public Point2d getPoint() {
        return this.point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) return false;

        ShotImpl that = (ShotImpl) o;

        return Objects.equal(this.point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(point);
    }

    @Override
    public String toString() {
        return this.point.toString();
    }
}
