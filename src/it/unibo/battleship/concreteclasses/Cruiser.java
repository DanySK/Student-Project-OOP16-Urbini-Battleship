package it.unibo.battleship.concreteclasses;

import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.model.AbstractShip;

public class Cruiser extends AbstractShip {

    public Cruiser(final Point2d startingPos, final Point2d endingPos) {
        super(startingPos, endingPos);
    }

    @Override
    public final int getDimension() {
        return 3;
    }

}
