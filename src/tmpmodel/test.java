package tmpmodel;

import java.util.Optional;

import tmpmodel.GlobalProperties.EnumNave;

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

        int i = 0;
        int j = 0;
        
        Field f1 = new Field(rows, columns);
        Field f2 = new Field(rows, columns);

        //Fleet fleet1 = Ruleset.getNewFleet();
        //Fleet fleet2 = Ruleset.getNewFleet();

        // PIAZZAMENTO NAVI
        Fleet fleet1 = new Fleet();
        Fleet fleet2 = new Fleet();

//        creaFlottaEPosiziona(fleet1);
//        creaFlottaEPosiziona(fleet2);
        creaFlotta(fleet1);
        creaFlotta(fleet2);

        for (AbstractShip s : fleet1.getAllNonPlacedShips()) {
            System.out.println(s.getClass().getSimpleName());
        }
        
        for (AbstractShip s : fleet1.getAllShipsByType(EnumNave.SUBMARINE)) {
            System.out.println(s.getClass().getSimpleName());
        }
        
        for (AbstractShip s : fleet1.getAllShipsByType(EnumNave.CRUISER)) {
            System.out.println(s.getClass().getSimpleName());
        }
        
        System.out.println("Iniziamo a posizionare...");
        while (!fleet1.isReady()) {
            AbstractShip s;
            System.out.println("OK");
            Optional<AbstractShip> tmp = fleet1.getNextShipByType(EnumNave.SUBMARINE);
            if (tmp.isPresent()) {
                s = tmp.get();
                System.out.println(s.getClass().getSimpleName());
                s.place(new Point2dImpl(j++, i++));
            }
            
        }
        // FASE DI COMBATTIMENTO
        if (fleet1.isReady() && fleet2.isReady()) {
            System.out.println("flotte pronte");

//            f1.placeFleet(fleet1);
//            f2.placeFleet(fleet2);

            stampa(fleet1);
            stampa(fleet2);

            System.out.println("\n\n\n\nstampa flotta 1...");
            

            // Creazione spari
            
            
            
        }
    }

    
    
    
    
    
    
    
    
    
    private static void stampa(final Fleet f) {
        System.out.println("Stampa flotta:");

        for ( final AbstractShip s : f.getAllShips()) {
            System.out.println(s.getClass().getSimpleName() + "; Pos : " + s.getPosition().get().getX() +" : " + s.getPosition().get().getY());
            
            for ( final Point2d p : s.getAllPositions()) {
                System.out.println(p.getX() + " : " + p.getY() );
            }
        }
        System.out.println();
    }

    private static void creaFlottaEPosiziona(final Fleet fleet) {
        int i = 0, j = 0;
        for (; i < Ruleset.getSubmarinesNumber(); i++, j++) {
//            fleet.addShip(new AbstractShip.Submarine(new Point2dImpl(j, i)));
        }

        for (; i < Ruleset.getSubmarinesNumber() + Ruleset.getCruisersNumber(); i++, j++) {
//            fleet.addShip(new AbstractShip.Cruiser(new Point2dImpl(j, i)));
        }
    }
    
    private static void creaFlotta(final Fleet fleet) {
        for (int i = 0; i < Ruleset.getSubmarinesNumber(); i++) {
//            fleet.addShip(new AbstractShip.Submarine());
        }

        for (int j = 0; j < Ruleset.getCruisersNumber(); j++) {
//            fleet.addShip(new AbstractShip.Cruiser());
        }
    }
}
