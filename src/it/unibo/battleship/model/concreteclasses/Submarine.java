package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.PointImpl;

public class Submarine extends AbstractShip {

    public Submarine(final PointImpl startingPos, final PointImpl endingPos) {
        super(startingPos, endingPos);
        // TODO Auto-generated constructor stub
    }

    @Override
    public final int getDimension() {
        return 2;
    }

}
