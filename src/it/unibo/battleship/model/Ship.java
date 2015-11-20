package it.unibo.battleship.model;

import java.util.List;

import it.unibo.battleship.common.Square;
import it.unibo.battleship.common.Point2d;

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
    Point2d getStartingPosition();

    /***
     *
     * @return 
     */
    Point2d getEndingPosition();

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
    boolean tryHit(Point2d point);
    /***
     *
     * @param startingPoint
     * @param endingPoint
     * @return
     */
    boolean move(Point2d startingPoint, Point2d endingPoint);
    
    /***
     *
     * @return
     */
    List<Square> getSquareList();
}
