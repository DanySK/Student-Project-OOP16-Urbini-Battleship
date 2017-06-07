package it.unibo.battleship.common;

/**
 * Represents the boundary of a field
 *
 * @author fabio.urbini
 */
public interface Boundary {

    /**
     *
     * @return the Horizontal bound (x)
     */
    int getColumnsCount();

    /**
     * @return the Vertical bound (y)
     */
    int getRowsCount();
}

