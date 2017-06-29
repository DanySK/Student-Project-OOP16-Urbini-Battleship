package it.unibo.battleship.extra;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.ships.FleetFactory;
import it.unibo.battleship.shots.ShotFactory;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * Represents an Artificial Intelligence which can be used for creating a new
 * Fleet or for creating new shots. The strategy is decided by implementations.
 *
 * @author fabio.urbini
 */
@Immutable
public interface ArtificialIntelligence extends Serializable {

  /**
   * Returns a {@link FleetFactory}
   * @return a {@link FleetFactory}
   */
  FleetFactory getFleetFactory();

  /**
   * Returns a {@link ShotFactory}
   * @return a {@link ShotFactory}
   */
  ShotFactory getShotFactory(); // TODO: make it a functional interface -> ShotFactory?

  /**
   * Returns the boundary.
   * @return the boundary.
   */
  Boundary getBoundary();
}
