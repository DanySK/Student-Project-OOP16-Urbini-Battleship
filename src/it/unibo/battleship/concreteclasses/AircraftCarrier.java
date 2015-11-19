package it.unibo.battleship.concreteclasses;

import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.model.AbstractShip;

public class AircraftCarrier extends AbstractShip {

    public AircraftCarrier(Point2d startingPos, Point2d endingPos) {
        super(startingPos, endingPos);
    }

    @Override
    public final int getDimension() {
        return 5;
    }
}
