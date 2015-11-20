package it.unibo.battleship.concreteclasses;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.model.Ship;

// Commentare
public class ShipFactory {
    
    private Boundary boundary;
    
    public ShipFactory(Boundary boundary) {
        this.boundary = boundary;
    }
    
    /**
     * Builds a new {@link Ship}.
     * @param dimension
     *              Dimension of the Ship
     * @param startIndex    
     *              Starting index of the ship
     * @param endIndex
     *              Ending index of the ship
     * @return returns a Ship
     */
    public final Ship createShip (final int dimension, final int startIndex,
            final int endIndex) {
        Ship retVal;
        Point2d start = new Point2d(startIndex, this.boundary);
        Point2d end = new Point2d(endIndex, this.boundary);
        
        // CODICE DUPLICATO : FARE METODO COMUNE
        switch (dimension) {
            case 2: retVal = new Submarine(start, end); break;
            case 3: retVal = new Cruiser(start, end); break;
            case 4: retVal = new Destroyer(start, end); break;
            case 5: retVal = new AircraftCarrier(start, end); break;
            default : retVal = null; // Ovviamente da correggere
        }
        
        return retVal;
    }
    // Commentare
    public static Ship createShip(final int dimension, final Point2d startingPos,
            final Point2d endingPos) {
        Ship retVal;
        switch (dimension) {
            case 2: retVal = new Submarine(startingPos, endingPos); break;
            case 3: retVal = new Cruiser(startingPos, endingPos); break;
            case 4: retVal = new Destroyer(startingPos, endingPos); break;
            case 5: retVal = new AircraftCarrier(startingPos, endingPos); break;
            default : retVal = null; // Ovviamente da correggere
        }
        return retVal;
    }
    
}
