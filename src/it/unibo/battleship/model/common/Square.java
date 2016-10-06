package it.unibo.battleship.model.common;


/***
 *
 * @author fabio
 * interface of a Square
 */
public interface Square { 

    /***
     *
     * @return returns the current position of the cell
     */
    PointImpl getCurrentPoint();
    /***
     *
     * @return returns the current state of a cell
     */
    State getState(); // Eliminare lo stato

    /***
     *
     * Sets a new state.
     * @param state is the new state.
     * @return returns true if it was OK.
     */
    boolean setState(State state);  // Anche questo da eliminare
}
