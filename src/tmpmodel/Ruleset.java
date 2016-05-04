package tmpmodel;

public final class Ruleset {
    // PATTERN COMMAND PER FARE METODI IN BASE ALLE NAVI ESISTENTI?

    private static final int N_SUBMARINES = 5;
    private static final int N_CRUISERS = 3;

    private static final int NORTHERN_LIMIT = 0;
    private static final int SOUTHERN_LIMIT = 10;
    private static final int EASTERN_LIMIT = 10;
    private static final int WESTERN_LIMIT = 0;

    private static final int SUBMARINE_SIZE = 2;
    private static final int CRUISER_SIZE = 3;
    private static final int BATTLESHIP_SIZE = 4;
    
    
    public static boolean isPointWithinLimits(Point2d p) {
        return (p.getY() >= NORTHERN_LIMIT && p.getY() <= SOUTHERN_LIMIT) &&
                (p.getX() >= WESTERN_LIMIT && p.getX() <= EASTERN_LIMIT );
    }

    public static Fleet getNewFleet(){
        Fleet fleet = new Fleet();

        // Creazione di 10 navi, aggiunte alla flotta
        for (int i = 0; i < N_SUBMARINES; i++) {
            fleet.addShip(new AbstractShip.Submarine());
        }

        for (int i = 0; i < N_CRUISERS; i++) {
            fleet.addShip(new AbstractShip.Cruiser());
        }
        return fleet;
    }

    // GET - SIZE
    public static int getSubmarineSize() {
        return Ruleset.SUBMARINE_SIZE;
    }

    public static int getCruiserSize() {
        return Ruleset.CRUISER_SIZE;
    }
    
    public static int getBattleshipSize() {
        return Ruleset.BATTLESHIP_SIZE;
    }
    
    // GET - NUMBER OF
    
    public static int getSubmarinesNumber() {
        return Ruleset.N_SUBMARINES;
    }
    
    public static int getCruisersNumber() {
        return Ruleset.N_CRUISERS;
    }
}
