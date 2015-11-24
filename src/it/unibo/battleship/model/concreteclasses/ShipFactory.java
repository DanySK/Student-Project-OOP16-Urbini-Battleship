package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.Boundary;
import it.unibo.battleship.model.common.PointImpl;
import it.unibo.battleship.model.interfaces.Ship;

// Commentare
public class ShipFactory {

    private final Boundary boundary;
    
    public ShipFactory(final Boundary boundary) {
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
        final PointImpl startingPos = new PointImpl(startIndex, this.boundary);
        final PointImpl endingPos = new PointImpl(endIndex, this.boundary);
        
        return createShipSwitch(dimension, startingPos, endingPos);
    }
    // Commentare
    public static Ship createShip(final int dimension, final PointImpl startingPos,
            final PointImpl endingPos) {
        return createShipSwitch(dimension, startingPos, endingPos);
    }

    private static Ship createShipSwitch(final int dimension,
            final PointImpl startingPos, final PointImpl endingPos) {
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
