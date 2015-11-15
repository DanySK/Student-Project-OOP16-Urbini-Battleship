package it.unibo.battleship.model;

import java.util.List;

import it.unibo.battleship.common.Cell;
import it.unibo.battleship.common.Point2d;

public interface Ship {
    
    /***
     * 
     * @return returns the dimension of the ship.
     */
    int getDimension();
    
    /***
     * 
     * @return
     */
    Point2d getStartingPoint();
    
    /***
     * 
     * @return
     */
    Point2d getEndingPoint();
    
    /***
     * 
     * @return
     */
    boolean isSank();
    
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
    List<Cell> getCellsList();
}
