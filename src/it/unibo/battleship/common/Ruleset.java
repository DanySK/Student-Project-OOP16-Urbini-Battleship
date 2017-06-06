package it.unibo.battleship.common;

import it.unibo.battleship.ships.Ship;

/**
 * Represents the ruleset of the battlefield game.
 * Some rules can be changed manually.
 */
public final class Ruleset {
    private static final int N_SUBMARINES = 3;
    private static final int N_CRUISERS = 2;
    private static final int N_BATTLESHIPS = 2;
    private static final Boundary boundary = BoundaryImpl.createBoundary(9, 9);

    private Ruleset() {}

    public static boolean isPointWithinLimits(final Point2d p) {
        return ( p.getY() >= 0 && p.getY() < boundary.getRowsCount()) &&
                ( p.getX() >= 0 && p.getX() < boundary.getColumnsCount() );
    }
    
    public static boolean isShipWithinLimits(final Ship ship, final Point2d point) {
        // Standard direction : east
        int length = ship.getSize();
        
        for (int x = point.getX(); x <(point.getX() + length); x++ ) {
            if (!Ruleset.isPointWithinLimits(new Point2dImpl(x, point.getY()))) {
                return false;
            }
        }
        return true;
    }

    public static Boundary getBoundary() {
        return boundary; // boundary is immutable
    }

    public static int getSubmarinesNumber() {
        return Ruleset.N_SUBMARINES;
    }
    
    public static int getCruisersNumber() {
        return Ruleset.N_CRUISERS;
    }
    
    public static int getBattleshipsNumber() {
        return Ruleset.N_BATTLESHIPS;
    }
}
