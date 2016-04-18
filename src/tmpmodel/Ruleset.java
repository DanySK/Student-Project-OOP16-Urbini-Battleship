package tmpmodel;

public final class Ruleset {
    static final int N_SUBMARINES = 10;
    
    
    public static final Fleet getNewFleet(){
        Fleet f1 = new Fleet();

        // Creazione di 10 navi, aggiunte alla flotta
        for (int i = 0; i < N_SUBMARINES; i++) {
            f1.addShip(new AbstractShip.Submarine());
        }
        
        return f1;
    }
    
    public static final int getSubmarineSize(){
        return 2;
    }
}
