package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.PointImpl;
import it.unibo.battleship.model.interfaces.AbstractShip;

public class AircraftCarrier extends AbstractShip {

    public AircraftCarrier(final PointImpl startingPos, final PointImpl endingPos) {
        super(Utilities.AIRCRAFT_CARRIER_DIMENSION, startingPos, endingPos);
    }
}
