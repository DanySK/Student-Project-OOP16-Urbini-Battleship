package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.Point2d;

public class Destroyer extends AbstractShip {

    public Destroyer(final Point2d startingPos, final Point2d endingPos) {
        super(startingPos, endingPos);
    }

    @Override
    public final int getDimension() {
        return 4;
    }

}
