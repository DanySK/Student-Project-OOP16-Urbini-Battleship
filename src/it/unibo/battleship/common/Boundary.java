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
    int getHorizontalBound();

    /**
     * @return the Vertical bound (y)
     */
    int getVerticalBound();
}
