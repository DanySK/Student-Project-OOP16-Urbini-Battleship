package it.unibo.battleship.concreteclasses;

import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.model.AbstractShip;

public class Destroyer extends AbstractShip {

    public Destroyer(final Point2d startingPos, final Point2d endingPos) {
        super(startingPos, endingPos);
    }

    @Override
    public final int getDimension() {
        return 4;
    }

}
