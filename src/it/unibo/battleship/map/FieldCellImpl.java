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
public final class FieldCellImpl implements FieldCell {
    private static final long serialVersionUID = 188175020723853008L;
    private State          currentState;
    private transient Optional<Ship> ship;
    // TODO: remove optional in the future

    public FieldCellImpl() {
        this.currentState = State.WATER;
        this.ship         = Optional.empty();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }

        final FieldCellImpl that = (FieldCellImpl) o;

        return Objects.equal(this.currentState, that.currentState) && Objects.equal(this.ship, that.ship);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.currentState, this.ship);
    }

    @Override
    public void placeShip(final Ship ship) {
        this.ship         = Optional.of(ship);
        this.currentState = State.PRESENT;
    }

    @Override
    public void shoot(final Shot shot) {
        switch (this.currentState) {
        case WATER :
            this.currentState = State.MISSED;
            break;

        case MISSED :
            break;    // Exception?

        case PRESENT :
            this.ship.ifPresent(ship -> {
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
                .add("currentState", this.currentState)
                .add("ship", this.ship)
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

