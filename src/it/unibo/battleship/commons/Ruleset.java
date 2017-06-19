package it.unibo.battleship.commons;

import it.unibo.battleship.ships.Ship;

import java.util.stream.IntStream;

/**
 * Represents the ruleset of the battlefield game.
 * Some rules can be changed manually.
 */
public final class Ruleset {
    public static final Boundary BOUNDARY              = BoundaryImpl.createBoundary(10, 10);
    public static final boolean  SHOOT_AGAIN_AFTER_HIT = false;

    private Ruleset() {}

    public static boolean isPointWithinLimits(final Point2d p) {
        return ((p.getY() >= 0) && (p.getY() < BOUNDARY.getRowsNumber()))
               && ((p.getX() >= 0) && (p.getX() < BOUNDARY.getColumnsNumber()));
    }

    public static boolean isShipWithinLimits(final Ship ship, final Point2d point) {

        // Standard direction : east
        int length = ship.getSize();

        return IntStream
                .range(point.getX(), (point.getX() + length))
                .allMatch(x -> Ruleset.isPointWithinLimits(new Point2dImpl(x, point.getY())));
    }
}

