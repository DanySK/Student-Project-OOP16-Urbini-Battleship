package it.unibo.battleship.map;

import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;

import java.util.Optional;

/**
 * Represents a field cell which can have
 * different states.
 * @author fabio.urbini
 *
 */
public interface FieldCell {
    void placeShip(Ship s);

    void shoot(Shot s);

    boolean isEmpty();

    /**
     * Returns {@code true} if a {@link Ship} is hit.
     * @return
     */
    boolean isHit();

    /**
     * Returns {@code true} if the field cell was hit but it was empty
     * @return {@code true} if the field cell was hit but it was empty
     */
    boolean isMissed();

    /**
     * Returns {@code true} if a {@link Ship} is present, false otherwise.
     * @return {@code true} if a {@link Ship} is present
     */
    boolean isPresent();

    /**
     * Returns an Optional of Ship
     * @return an Optional of Ship
     */
    Optional<Ship> getShip();
}

