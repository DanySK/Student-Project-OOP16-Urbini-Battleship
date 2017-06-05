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
	boolean isSunk();

	int getSize();

	// METODO OPTIONAL - pu? ritornare null
	Optional<Point2d> getPosition();

	// Fare metodo cached
	// RESTITUISCE I PUNTI OCCUPATI DALLA NAVE
	// OPTIONAL!!!!
	List<Point2d> getAllPositions();

	// restituisce tutti i punti occupati virtualmente
	List<Point2d> getProjectionPoints (Point2d point);

	boolean containsPosition(Point2d point);

	void place(Point2d start);

	boolean isPlaced();

	boolean shoot (Shot shot);

	void resetPlacement();
}
