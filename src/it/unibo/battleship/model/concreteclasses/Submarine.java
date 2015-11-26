package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.PointImpl;
import it.unibo.battleship.model.interfaces.AbstractShip;

public class Submarine extends AbstractShip {

    public Submarine(final PointImpl startingPos, final PointImpl endingPos) {
        super(Utilities.SUBMARINE_DIMENSION, startingPos, endingPos);
    }
}
