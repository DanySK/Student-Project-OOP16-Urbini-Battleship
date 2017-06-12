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

    private AbstractShip() {
        this.pos           = Optional.empty();
        this.placed        = false;
        this.direction = ShipDirection.EAST;
        hitPoints          = new ArrayList<>();
    }

    private AbstractShip(final Point2d start) {
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

    /**
     * Creates a ship
     * @param size size of the ship. The value must be
     * between 0 and {@see GlobalProperties.MAX_SIZE}
     * @return the ship created
     */
    public static Ship createShip(final int size) {
        if ((size < 0) || (size > GlobalProperties.MAX_SIZE)) {
            throw new IllegalArgumentException(GlobalProperties.INVALID_SHIP_SIZE);
        }

        switch (size) {
        case GlobalProperties.SUBMARINE_SIZE :
            return new Submarine();

        case GlobalProperties.CRUISER_SIZE :
            return new Cruiser();

        case GlobalProperties.BATTLESHIP_SIZE :
            return new Battleship();

        case GlobalProperties.AIR_CARRIER_SIZE :
            return new AirCarrier();

        default :
            throw new IllegalArgumentException(GlobalProperties.INVALID_SHIP_SIZE);
        }
    }

    @Override
    public boolean equals(Object o) {
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
    public abstract String toString();

    @Override
    public List<Point2d> getAllPositions() {
        // TODO: direction currently not used (EAST direction as default).

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

    private static final class AirCarrier extends AbstractShip {
        private static final long serialVersionUID = -8323321815851042898L;

        AirCarrier() {
            super();
        }

        @SuppressWarnings("unused")
        AirCarrier(final Point2d start) {
            super(start);
        }

        @Override
        public String toString() {
            return GlobalProperties.ShipRules.AIR_CARRIER.toString();
        }

        @Override
        public int getSize() {
            return GlobalProperties.AIR_CARRIER_SIZE;
        }
    }


    private static final class Battleship extends AbstractShip {
        private static final long serialVersionUID = 8043537272411631772L;

        private Battleship() {
            super();
        }

        @SuppressWarnings("unused")
        private Battleship(final Point2d start) {
            super(start);
        }

        @Override
        public String toString() {
            return GlobalProperties.ShipRules.BATTLESHIP.toString();
        }

        @Override
        public int getSize() {
            //return GlobalProperties.ShipRules.BATTLESHIP.getSize();
        	return GlobalProperties.BATTLESHIP_SIZE;
        }
    }


    private static final class Cruiser extends AbstractShip {
        private static final long serialVersionUID = -5532557604937632667L;

        private Cruiser() {
            super();
        }

        @SuppressWarnings("unused")
        private Cruiser(final Point2d start) {
            super(start);
        }

        @Override
        public String toString() {
            return GlobalProperties.ShipRules.CRUISER.toString();
        }

        @Override
        public int getSize() {
            //return GlobalProperties.ShipRules.CRUISER.getSize();
        	return GlobalProperties.CRUISER_SIZE;
        }
    }


    private static final class Submarine extends AbstractShip {
        private static final long serialVersionUID = -2784639518931814680L;

        Submarine() {
            super();
        }

        @SuppressWarnings("unused")
        Submarine(final Point2d start) {
            super(start);
        }

        @Override
        public String toString() {
            return GlobalProperties.ShipRules.SUBMARINE.toString();
        }

        @Override
        public int getSize() {
            //return GlobalProperties.ShipRules.SUBMARINE.getSize();
        	return GlobalProperties.SUBMARINE_SIZE;
        }
    }
}

