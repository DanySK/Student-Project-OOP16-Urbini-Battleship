package it.unibo.battleship.commons;

import java.io.Serializable;

/**
 * Represents the boundary of a field
 * @author fabio.urbini
 */
public interface Boundary extends Serializable {

    /**
     * Returns the horizontal bound, which is also the number of columns
     * @return the Horizontal bound (x)
     */
    int getColumnsNumber();

    /**
     * Returns the vertical bound, which is also the number of rows
     * @return the Vertical bound (y)
     */
    int getRowsNumber();
}

