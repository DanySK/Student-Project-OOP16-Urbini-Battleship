package it.unibo.battleship.commons;

import java.io.Serializable;

/**
 * Represents a 2 dimension point
 * @author fabio.urbini
 *
 */
public interface Point2d extends Serializable {

    /**
     * Returns the X coordinate (column)
     * @return the x coordinate (column)
     */
    int getX();

    /**
     * Returns the y coordinate (row)
     * @return the y coordinate (row)
     */
    int getY();
}

