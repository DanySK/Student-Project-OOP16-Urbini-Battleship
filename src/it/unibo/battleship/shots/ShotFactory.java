package it.unibo.battleship.shots;

import java.io.Serializable;

/**
 * Represents a Shot Factory
 * @author fabio.urbini
 */
public interface ShotFactory extends Serializable {
  Shot createShot();
}
