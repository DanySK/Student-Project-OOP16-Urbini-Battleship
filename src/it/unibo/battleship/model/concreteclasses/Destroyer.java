package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.PointImpl;

public class Destroyer extends AbstractShip {

    public Destroyer(final PointImpl startingPos, final PointImpl endingPos) {
        super(startingPos, endingPos);
    }

    @Override
    public final int getDimension() {
        return 4;
    }

}
