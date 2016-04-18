package tmpmodel;

public final class Ruleset {
    static final int N_SUBMARINES = 5;
    static final int N_CRUISERS = 3;

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
