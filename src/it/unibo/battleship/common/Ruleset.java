package it.unibo.battleship.common;

import it.unibo.battleship.ships.Ship;

import java.util.stream.IntStream;

/**
 * Represents the ruleset of the battlefield game.
 * Some rules can be changed manually.
 */
public final class Ruleset {
    private static final Boundary BOUNDARY              = BoundaryImpl.createBoundary(10, 10);
    private static final boolean  SHOOT_AGAIN_AFTER_HIT = false;

    private Ruleset() {}

    @SuppressWarnings("unused")
    public static boolean canShootAgainAfterHit() {
        return SHOOT_AGAIN_AFTER_HIT;
    }

    public static Boundary getBoundary() {
        return BOUNDARY;    // boundary is immutable
    }

    public static boolean isPointWithinLimits(final Point2d p) {
        return ((p.getY() >= 0) && (p.getY() < BOUNDARY.getRowsCount()))
               && ((p.getX() >= 0) && (p.getX() < BOUNDARY.getColumnsCount()));
    }

    public static boolean isShipWithinLimits(final Ship ship, final Point2d point) {

        // Standard direction : east
        int length = ship.getSize();

        return IntStream
                .range(point.getX(), (point.getX() + length))
                .allMatch(x -> Ruleset.isPointWithinLimits(new Point2dImpl(x, point.getY())));
    }
}

