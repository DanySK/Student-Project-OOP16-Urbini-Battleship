package tmpmodel;

public class gameTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // CREAZIONE FIELD e FLOTTA
        Field field1 = new Field(Ruleset.getRows(), Ruleset.getColumns());
        Fleet fleet1 = Ruleset.getNewFleet();

        int i = 0, j = 0;

        // PIAZZAMENTO FLOTTA
        while (!fleet1.isReady()) {
            AbstractShip ship;
            if (fleet1.getNextNonPlacedShip().isPresent()) {
               ship = fleet1.getNextNonPlacedShip().get();
               field1.placeShip(ship, new Point2dImpl(i++, j++));

            }

            stampaField(field1);
            System.out.println("\n\n\n");
        }

        System.out.println("FLOTTA PRONTA!!");

        i = 0;
        j = 0;

        // SPARA FINCHE' LA FLOTTA NON E' AFFONDATA
        while (!fleet1.isSunk()) {
            for (i = 0; i < Ruleset.getRows() && !fleet1.isSunk(); i++) {
                for (j = 0; j < Ruleset.getColumns() && !fleet1.isSunk(); j++) {
                    Shot shot = new Shot(new Point2dImpl(j, i));
                    System.out.println("Sparo in posizione : " + i + ";" + j);
                    field1.updateStateWithShot(shot);

                }
            }
        }

        System.out.println("VINTO!");
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
