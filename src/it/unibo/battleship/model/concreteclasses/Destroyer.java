package it.unibo.battleship.model.concreteclasses;

import it.unibo.battleship.model.common.PointImpl;
import it.unibo.battleship.model.interfaces.AbstractShip;

public class Destroyer extends AbstractShip {

    public Destroyer(final PointImpl startingPos, final PointImpl endingPos) {
        super(Utilities.DESTROYER_DIMENSION, startingPos, endingPos);
    }
}
