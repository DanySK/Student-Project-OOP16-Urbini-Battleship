package it.unibo.battleship.ships;

import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.shots.Shot;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by fabio.urbini on 05/06/2017.
 */
public interface Ship extends Serializable {
    boolean containsPosition(Point2d point);

    void place(Point2d start);

    void resetPlacement();

    boolean shoot(Shot shot);

    List<Point2d> getAllPositions();

    boolean isPlaced();

    Optional<Point2d> getPosition();

    List<Point2d> getProjectionPoints(Point2d point);

    int getSize();

    boolean isSunk();
}

