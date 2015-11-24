package it.unibo.battleship.model.interfaces;

import java.util.List;

import it.unibo.battleship.model.common.PointImpl;
import it.unibo.battleship.model.common.Square;

/***
 * This interface represents a Ship and its public methods.
 * @author Fabio Urbini
 * 
 */
public interface Ship {

    /***
     * Gets the type of the ship
     * @return returns a string
     */
    String getType();
    /***
     * Gets the dimension of the ship.
     * @return returns a int value
     */
    int getDimension();

    /***
     *
     * @return
     */
    PointImpl getStartingPosition();

    /***
     *
     * @return 
     */
    PointImpl getEndingPosition();
    
    // getDirection

    /***
     *
     * @return
     */
    boolean isSunk();

    /***
     *
     * @return
     */
    boolean isPlaced();

    /***
     *
     * @param point
     * @return
     */
    boolean tryHit(PointImpl point);
    /***
     *
     * @param startingPoint
     * @param endingPoint
     * @return
     */
    boolean move(PointImpl startingPoint, PointImpl endingPoint);
    
    /***
     *
     * @return
     */
    List<Square> getSquareList();
}
