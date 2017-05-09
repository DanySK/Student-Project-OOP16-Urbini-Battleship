package tmpmodel;

public class Twoplayersgame {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Field field1 = new Field(Ruleset.getRows(), Ruleset.getColumns());
        Field field2 = new Field(Ruleset.getRows(), Ruleset.getColumns());
        
        Fleet fleet1 = Ruleset.getNewFleet();
        Fleet fleet2 = Ruleset.getNewFleet();
        
        creaFlotta(field1, fleet1);
        creaFlotta(field2, fleet2);
        
        for (int i = 0; i < 10; i++ ) {
            for (int j = 0; j < 10; j++ )
                field1.updateStateWithShot(Shot.createShot(new Point2dImpl(j,i)));
                if (fleet1.isSunk()) {
                    System.out.println("AFFONDATA");
                    break;
                }
        }
    }
    
    private static void creaFlotta(Field field1, Fleet fleet1) {
        int i = 0, j = 0;

        // PIAZZAMENTO FLOTTA
        AbstractShip ship;
        while (!fleet1.isReady()) {

            if (fleet1.getNextNonPlacedShip().isPresent()) {
               ship = fleet1.getNextNonPlacedShip().get();
               field1.placeShip(ship, new Point2dImpl(i++, j++));

            }
        }
        
        stampaField(field1);
        System.out.println("\n\n\n");
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
