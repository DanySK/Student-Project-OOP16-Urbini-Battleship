package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.Point2d;

public class Submarine extends AbstractShip {

    public Submarine(final Point2d startingPos, final Point2d endingPos) {
        super(startingPos, endingPos);
        // TODO Auto-generated constructor stub
    }

    @Override
    public final int getDimension() {
        return 2;
    }

}
