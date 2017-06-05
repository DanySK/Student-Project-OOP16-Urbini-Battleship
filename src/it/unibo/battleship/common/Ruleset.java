package it.unibo.battleship.common;

import it.unibo.battleship.ships.AbstractShip;

public final class Ruleset {
    private static final int N_SUBMARINES = 3;
    private static final int N_CRUISERS = 2;
    private static final int N_BATTLESHIPS = 2;

    private static final Boundary boundary = BoundaryImpl.createBoundary(9, 9);
    private static final int ROWS = boundary.getVerticalBound();
    private static final int COLUMNS = boundary.getHorizontalBound();

    private Ruleset() {}

    public static boolean isPointWithinLimits(final Point2d p) {
        return ( p.getY() >= 0 && p.getY() < Ruleset.ROWS) &&
                ( p.getX() >= 0 && p.getX() <= Ruleset.COLUMNS );
    }
    
    public static boolean isShipWithinLimits(final AbstractShip ship, final Point2d point) {
        // Standard direction : east
        int length = ship.getSize();
        
        for (int x = point.getX(); x <(point.getX() + length); x++ ) {
            if (!Ruleset.isPointWithinLimits(new Point2dImpl(x, point.getY()))) {
                return false;
            }
        }
        return true;
    }

    public static int getRows() {
        return Ruleset.ROWS;
    }
    
    public static int getColumns() {
        return Ruleset.COLUMNS;
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
