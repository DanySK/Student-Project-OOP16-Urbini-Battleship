package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.PointImpl;
import it.unibo.battleship.model.interfaces.AbstractShip;

public class Cruiser extends AbstractShip {

    public Cruiser(final PointImpl startingPos, final PointImpl endingPos) {
        super(Utilities.CRUISER_DIMENSION, startingPos, endingPos);
    }
}
