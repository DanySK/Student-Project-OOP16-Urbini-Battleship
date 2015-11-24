package it.unibo.battleship.model.interfaces;

import java.util.Optional;

/**
 * Interface wrapping a Point
 * @author fabio
 *
 */
public interface Position {

    Point getPoint();
    
    Optional<Integer> getIndex();
    
    Optional<Border> getBorder();
}
