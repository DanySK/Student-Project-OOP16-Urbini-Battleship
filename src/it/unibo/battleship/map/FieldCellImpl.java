package it.unibo.battleship.map;

import java.util.Optional;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;

/**
 * An implementation of a {@link FieldCell}
 * @author fabio.urbini
 *
 */
public class FieldCellImpl implements FieldCell {
    private State          currentState;
    private Optional<Ship> ship;    // TODO: remove optional in the future

    public FieldCellImpl() {
        this.currentState = State.WATER;
        this.ship         = Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        final FieldCellImpl that = (FieldCellImpl) o;

        return Objects.equal(this.currentState, that.currentState) && Objects.equal(this.ship, that.ship);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currentState, ship);
    }

    @Override
    public void placeShip(final Ship ship) {
        this.ship         = Optional.of(ship);
        this.currentState = State.PRESENT;
    }

    @Override
    public void shoot(final Shot shot) {
        switch (currentState) {
        case WATER :
            this.currentState = State.MISSED;
            break;

        case MISSED :
            break;    // Exception?

        case PRESENT :
            ship.ifPresent(ship -> {
                this.currentState = State.HIT;
                ship.shoot(shot);
            });
            break;

        case HIT :
            break;    // Exception?

        default :
            throw new IllegalArgumentException("Invalid state value");
        }
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("currentState", currentState)
                .add("ship", ship)
                .toString();
    }

    @Override
    public boolean isEmpty() {
        return this.currentState == State.WATER;
    }

    @Override
    public boolean isHit() {
        return this.currentState == State.HIT;
    }

    @Override
    public boolean isMissed() {
        return this.currentState == State.MISSED;
    }

    @Override
    public boolean isPresent() {
        return this.currentState == State.PRESENT;
    }

    @Override
    public Optional<Ship> getShip() {
        return this.ship;
    }

}

