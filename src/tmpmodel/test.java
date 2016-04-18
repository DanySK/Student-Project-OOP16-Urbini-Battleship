package tmpmodel;

public class test {

    public static void main(String[] args) {
        
        // Creazione 2 field
        // Creazione 2 fleet
        // Creazione 5 ship per fleet
        // Creazione shots con dei while
        // Controllo della flotta ad ogni sparo
        int rows = 10;
        int columns = 10;
        int nShips = 5;
        
        Field f1 = new Field(rows, columns);
        Field f2 = new Field(rows, columns);
        
        Fleet fleet1 = Ruleset.getNewFleet();
        Fleet fleet2 = Ruleset.getNewFleet();
        

        
        // FASE DI COMBATTIMENTO
        if (fleet1.isReady() && fleet2.isReady()) {
            
            f1.placeFleet(fleet1);
            f1.placeFleet(fleet2);
            
            
            
        }
    }
}
