package it.unibo.battleship.shots;

import java.io.Serializable;

/**
 * Represents a Shot Factory.
 * @author fabio.urbini
 */
@FunctionalInterface
public interface ShotFactory extends Serializable {
  Shot createShot();
}
