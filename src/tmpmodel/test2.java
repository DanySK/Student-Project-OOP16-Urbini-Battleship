package tmpmodel;

import java.util.Optional;

public class test2 {

    public static void main(String[] args) {

        // PASSAGGIO 1 : Creazione di un solo campo e una sola flotta
        int rows = 10;
        int columns = 10;

        int i = 0;
        int j = 0;

        Field field1 = new Field(rows, columns);
        Fleet fleet1 = Ruleset.getNewFleet();

        System.out.println("fleet size: " + fleet1.getAllNonPlacedShips().size());
        fleet1.getNextNonPlacedShip().get().place(new Point2dImpl(i,j));
        System.out.println("fleet size: " + fleet1.getAllNonPlacedShips().size());

        // ERRORE QUI, sto piazzando nello stesso punto --> LA FLOTTA NON LO VEDE
        fleet1.getNextNonPlacedShip().get().place(new Point2dImpl(i,j));
        System.out.println("fleet size: " + fleet1.getAllNonPlacedShips().size());
        // il numero di navi non piazzate dovrebbe esser uguale a prima


        for (int x = 0, y = 0; x < 12; x++, y++) {
            Point2d p = new Point2dImpl(x,y);
            System.out.println("Point : " + p.getX() + ";" + p.getY());
            System.out.println(Ruleset.isPointWithinLimits(p));
        }

        for (int x = -10, y = 10; y < 20; x++, y++) {
            Point2d p = new Point2dImpl(x,y);
            System.out.println("Point : " + p.getX() + ";" + p.getY());
            System.out.println(Ruleset.isPointWithinLimits(p));
        }

        for (int x = 0, y = 10; y < 20; x++, y++) {
            Point2d p = new Point2dImpl(x,y);
            System.out.println("Point : " + p.getX() + ";" + p.getY());
            System.out.println(Ruleset.isPointWithinLimits(p));
        }

        // PIAZZAMENTO FLOTTA
        System.out.println("Flotta pronta : " + fleet1.isReady());

        while (!fleet1.isReady()) {
            Optional<AbstractShip> ship = fleet1.getNextNonPlacedShip();

            // Se c'è una nave piazzabile, viene piazzata
            if (ship.isPresent()) {
                AbstractShip tmp = ship.get();
                tmp.place(new Point2dImpl(++i, ++j));

                // Controllo sulla flotta
                if (fleet1.isReady()) {
                    System.out.println("FLOTTA PRONTA!!!");
                }
            }
        }
        // FINE PIAZZAMENTO
        stampaFlotta(fleet1);

        fleet1.resetFleetPlacement();
        System.out.println("\n\n\n\nReset flotta\n\n\n\n");

        // STAMPA FLOTTA
        stampaFlotta(fleet1);

        // PIAZZAMENTO FLOTTA A PARTIRE DA FIELD
        stampaField(field1);

        i = 0;
        j = 0;
        // ERRORE POSSIBILE SU OPTIONAL
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));
        field1.placeShip(fleet1.getNextNonPlacedShip().get(), new Point2dImpl(++i, ++j));

        System.out.println();
        stampaField(field1);
        
        
    }

    private static void stampaFlotta (final Fleet fleet) {
        for (AbstractShip ship : fleet.getAllShips()) {
            String tmp = "";
            if (ship.isPlaced()) {
                tmp = ship.getPosition().get().getX() + ";" + ship.getPosition().get().getY();
            }
            System.out.println("-" + ship.toString() + " : " + tmp);
        }
    }

    private static void stampaField (final Field field) {
        for (char[] chars : field.getMatrix() ) {
            for (char car : chars ) {
                System.out.print(car);
            }
            System.out.println();
        }
    }
}
