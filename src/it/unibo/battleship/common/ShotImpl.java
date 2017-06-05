package it.unibo.battleship.common;

import com.google.common.base.Objects;

/*
TODO: estrarre interfaccia, creare metodo AI
 */
/**
 * Represents a single shot which can be
 * thrown on the battlefield.
 * @author fabio.urbini
 */
public final class ShotImpl {
    private final Point2d point;

    private ShotImpl(final Point2d p) {
        this.point = p;
    }

    public Point2d getPoint() {
        return this.point;
    }

    public static ShotImpl createShot(final Point2d p) {
        if (Ruleset.isPointWithinLimits(p)) {
            return new ShotImpl(p);
        } else throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS_EX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
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
