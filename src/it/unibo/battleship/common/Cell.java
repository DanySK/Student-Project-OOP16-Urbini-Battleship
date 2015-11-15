package it.unibo.battleship.common;


/***
 *
 * @author fabio
 * interface of a Cell
 */
public interface Cell { // NOPMD by fabio on 15/11/15 1.17

    /***
     *
     * @return returns the current position of the cell
     */
    Point2d getCurrentPoint();
    /***
     *
     * @return returns the current state of a cell
     */
    State getState();
}
