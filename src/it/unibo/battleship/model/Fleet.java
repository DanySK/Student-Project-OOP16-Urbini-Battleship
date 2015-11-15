package it.unibo.battleship.model;

import java.util.List;

import it.unibo.battleship.common.Point2d;

/***
 *
 * @author fabio
 *
 */
public interface Fleet {

    /***
     *
     * @param point coordinate of the point which is wanted to be hit.
     * @return returns true if the fleet was hit.
     */
    boolean tryHit(Point2d point);

    /***
     *
     * @return returns true if the fleet is placed and the game can start.
     */
    boolean isPlaced();
    
    /***
     *
     * @return returns true if the fleet was sank.
     */
    boolean isSank();
    
    /***
     *
     * @param ship ship that wants to be added.
     * @return returns true if the new ship was added.
     */
    boolean addShip(Ship ship);
    
    /***
     *
     * @return returns the list of the ships owned by the fleet.
     */
    List<Ship> getShips();
    
}
