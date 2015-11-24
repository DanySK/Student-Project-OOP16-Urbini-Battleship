package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.PointImpl;

public class AircraftCarrier extends AbstractShip {

    public AircraftCarrier(final PointImpl startingPos, final PointImpl endingPos) {
        super(startingPos, endingPos);
    }

    @Override
    public final int getDimension() {
        return 5;
    }
}
