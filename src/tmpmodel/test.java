package tmpmodel;

public class test {

    public static void main(String[] args) {
        // OGNI CELLA CON PRESENT HA LA NAVE
        // field cerca di piazzare la nave -> prima si verifica che possa stare nello spazio
        // poi si verifica che non ci siano altre navi vicine
        
        
        // nave affondata -> evento
        /* PROBLEMA :
         * Come piazzare tutte le navi non piazzate? Come ordinarle e modificarle? 
         * Le flotte ricordano dove sono state colpite? Se affondate (cached?) ? 
         * Field è un risultato di shots + fleet? 
         */

        // Creazione 2 field
        // Creazione 2 fleet
        // Creazione 5 ship per fleet
        // Creazione shots con dei while
        // Controllo della flotta ad ogni sparo
        int rows = 10;
        int columns = 10;

        Field f1 = new Field(rows, columns);
        Field f2 = new Field(rows, columns);

        //Fleet fleet1 = Ruleset.getNewFleet();
        //Fleet fleet2 = Ruleset.getNewFleet();

        // PIAZZAMENTO NAVI
        Fleet fleet1 = new Fleet();
        Fleet fleet2 = new Fleet();

        creaFlotta(fleet1);
        creaFlotta(fleet2);

        // FASE DI COMBATTIMENTO
        if (fleet1.isReady() && fleet2.isReady()) {
            System.out.println("flotte pronte");

//            f1.placeFleet(fleet1);
//            f2.placeFleet(fleet2);

            stampa(fleet1);
            stampa(fleet2);


            // Creazione spari
            
            
            
        }
    }

    
    
    
    
    
    
    
    
    
    private static void stampa(Fleet f) {
        System.out.println("Stampa flotta:");

        for ( final AbstractShip s : f.getAllShips()) {
            System.out.println(s.getClass().getSimpleName() + "; Pos : " + s.getPos().getX() +" : " + s.getPos().getY());
            
            for ( final Point2d p : s.getAllPositions()) {
                System.out.println(p.getX() + " : " + p.getY() );
            }
        }
        System.out.println();
    }

    private static void creaFlotta(Fleet fleet) {
        int i = 0, j = 0;
        for (; i < Ruleset.N_SUBMARINES; i++, j++) {
            fleet.addShip(new AbstractShip.Submarine(new Point2dImpl(j, i)));
        }

        for (; i < Ruleset.N_SUBMARINES + Ruleset.N_CRUISERS; i++, j++) {
            fleet.addShip(new AbstractShip.Cruiser(new Point2dImpl(j, i)));
        }
    }
}
