package tmpmodel;

public final class Ruleset {
    // PATTERN COMMAND PER FARE METODI IN BASE ALLE NAVI ESISTENTI?
    
    static final int N_SUBMARINES = 5;
    static final int N_CRUISERS = 3;
    
    static final int NORTHERN_LIMIT = 0;
    static final int SOUTHERN_LIMIT = 10;
    static final int EASTERN_LIMIT = 10;
    static final int WESTERN_LIMIT = 0;
    
    public static boolean isPointWithinLimits(Point2d p) {
        return (p.getY() >= NORTHERN_LIMIT && p.getY() <= SOUTHERN_LIMIT) && 
                (p.getX() >= WESTERN_LIMIT && p.getX() <= EASTERN_LIMIT );
                
    }
    
    public static final Fleet getNewFleet(){
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

    // magic numbers?
    public static final int getSubmarineSize() {
        return 2;
    }

    public static final int getCruiserSize() {
        return 3;
    }
}
