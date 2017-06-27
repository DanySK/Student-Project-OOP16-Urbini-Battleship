package it.unibo.battleship.shots;

import it.unibo.battleship.commons.Point2d;
import java.io.Serializable;

/**
 * Represents a single shot which can be thrown on the battlefield.
 *
 * @author fabio.urbini
 */
public interface Shot extends Serializable {
  /**
   * Gets the point of the shot.
   * @return the point of the shot.
   */
  Point2d getPoint();
}
