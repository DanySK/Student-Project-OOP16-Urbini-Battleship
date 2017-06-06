package it.unibo.battleship.map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.ships.Ship;

import java.util.Optional;

/**
 * An implementation of a {@link FieldCell}
 * @author fabio.urbini
 *
 */
public class FieldCellImpl implements FieldCell {
    private State currentState;
    private Optional<Ship> ship; // TODO: remove optional?

    public FieldCellImpl() {
        this.currentState = State.WATER;
        this.ship = Optional.empty();
    }

    @Override
	public void placeShip(final Ship s) {
        this.ship = Optional.of(s);
        this.currentState = State.PRESENT;
    }

    @Override
	public void shoot(final Shot s ) {
        switch (currentState) {
            case WATER: this.currentState = State.MISSED; break;
            case MISSED : break; // Exception?
            case PRESENT :
                this.currentState = State.HIT;
                this.ship.get().shoot(s);
                break;
            case HIT : break; // Exception?
            default : throw new IllegalArgumentException("Invalid state value");

        }
    }

    public Optional<Ship> getShip() {
    	return this.ship;
    }

    @Override
	public boolean isMissed() {
        return this.currentState == State.MISSED;
    }

    @Override
	public boolean isEmpty() {
        return this.currentState == State.WATER;
    }

    @Override
	public boolean isPresent() {
        return this.currentState == State.PRESENT;
    }

    @Override
    public boolean isHit() {
        return this.currentState == State.HIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final FieldCellImpl that = (FieldCellImpl) o;

        return Objects.equal(this.currentState, that.currentState) &&
                Objects.equal(this.ship, that.ship);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currentState, ship);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentState", currentState)
                .add("ship", ship)
                .toString();
    }
}
