package it.unibo.battleship.ships;

import com.google.common.base.Objects;
import it.unibo.battleship.commons.GlobalProperties;
import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.shots.Shot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This abstract class is to represent a Ship.
 * @author fabio.urbini
 *
 */
public abstract class AbstractShip implements Ship {
	/*
	 * This class hasn't got any abstract method yet except for
	 * toString. It was made abstract for future purposes.
	 * ie: a ship implementation can take more/less damage than
	 * other ships.
	 */
    private static final long serialVersionUID = 4917741645660917676L;
    private ShipDirection direction;
    private Optional<Point2d>   pos;    // TODO: remove optional in the future
    private boolean             placed;
    private final List<Point2d> hitPoints;

    protected AbstractShip() {
        this.pos           = Optional.empty();
        this.placed        = false;
        this.direction = ShipDirection.EAST;
        hitPoints          = new ArrayList<>();
    }

    protected AbstractShip(final Point2d start) {
        this();
        this.pos    = Optional.of(start);
        this.placed = true;
    }

    @Override
    public boolean containsPosition(final Point2d point) {
        return this.getAllPositions()
                   .stream()
                   .anyMatch(p -> ((p.getX() == point.getX()) && (p.getY() == point.getY())));
    }

    @Override
    public void place(final Point2d start, final ShipDirection direction) {
        if (!placed) {
            this.pos = Optional.of(start);
            placed   = true;
        } else {
            this.pos = Optional.of(start);
        }
        this.direction = direction;
    }

    @Override
    public void resetPlacement() {
        this.placed = false;
        pos         = Optional.empty();
    }

    @Override
    public boolean shoot(final Shot shot) {
        if (containsPosition(shot.getPoint())) {
            hitPoints.add(shot.getPoint());
            return true;
        }
        return false;
    }

    @Override
    public List<Point2d> getAllPositions() {
        // TODO: direction currently not used (EAST direction as default).
        if (!pos.isPresent()) {
            throw new IllegalStateException(GlobalProperties.STARTING_POSITION_NOT_DEFINED);
        }
        return IntStream.range(0, this.getSize())
                                     .mapToObj(i -> new Point2dImpl(pos.get().getX() + i, pos.get().getY()))
                                     .collect(Collectors.toList());
    }

    @Override
    public boolean isPlaced() {
        return this.placed;
    }

    @Override
    public Optional<Point2d> getStartingPosition() {
        return this.pos;
    }

    @Override
    public List<Point2d> getProjectionPoints(final Point2d point) {
        // TODO: direction currently not used (EAST direction as default).

        return IntStream.range(point.getX(), (point.getX() + this.getSize()))
                                        .mapToObj(x -> new Point2dImpl(x, point.getY()))
                                        .collect(Collectors.toList());
    }

    @Override
    public boolean isSunk() {
        return this.hitPoints.size() >= this.getSize();

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        final AbstractShip that = (AbstractShip) o;

        return Objects.equal(this.direction, that.direction)
               && Objects.equal(this.pos, that.pos)
               && Objects.equal(this.placed, that.placed)
               && Objects.equal(this.hitPoints, that.hitPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(direction, pos, placed, hitPoints);
    }

    @Override
    public abstract String toString();

}

