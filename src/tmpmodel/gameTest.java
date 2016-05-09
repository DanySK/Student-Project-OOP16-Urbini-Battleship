package tmpmodel;

public class gameTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // Piazzamento flotta 
        Field field1 = new Field(Ruleset.getRows(), Ruleset.getColumns());
        Fleet fleet1 = Ruleset.getNewFleet();
        
        int i = 0, j = 0;
        
        while ( !fleet1.isReady()) {
            AbstractShip ship;
            if (fleet1.getNextNonPlacedShip().isPresent()) {
               ship = fleet1.getNextNonPlacedShip().get(); 
               field1.placeShip(ship, new Point2dImpl(i++, j++));
               
            }

            stampaField(field1);
            System.out.println("\n\n\n");
        }    
        
        System.out.println("FLOTTA PRONTA!!");
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
