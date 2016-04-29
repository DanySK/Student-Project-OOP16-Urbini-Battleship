package tmpmodel;

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
        
        // ERRORE QUI, sto piazzando nello stesso punto
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
        //  CREAZIONE DELLA FLOTTA
        
        
        
    }
}
