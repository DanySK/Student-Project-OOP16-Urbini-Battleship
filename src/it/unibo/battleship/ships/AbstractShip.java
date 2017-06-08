package it.unibo.battleship.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Objects;

import it.unibo.battleship.common.*;
import it.unibo.battleship.shots.Shot;

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

    // Standard direction = EAST (default)
    private ShipDirection       shipDirection;
    private Optional<Point2d>   pos;    // final - OPTIONAL?
    private boolean             placed;
    private final List<Point2d> hitPoints;

    private AbstractShip() {
        this.pos           = Optional.empty();
        this.placed        = false;
        this.shipDirection = ShipDirection.EAST;
        hitPoints          = new ArrayList<Point2d>();
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
     * @return
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
            throw new IllegalArgumentException("Valore non valido");
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

        return Objects.equal(this.shipDirection, that.shipDirection)
               && Objects.equal(this.pos, that.pos)
               && Objects.equal(this.placed, that.placed)
               && Objects.equal(this.hitPoints, that.hitPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(shipDirection, pos, placed, hitPoints);
    }

    @Override
    public void place(final Point2d start) {
        if (!placed) {
            this.pos = Optional.of(start);
            placed   = true;
        } else {
            this.pos = Optional.of(start);
        }
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
        final List<Point2d> tmp = IntStream.range(0, this.getSize())
                                     .mapToObj(i -> new Point2dImpl(pos.get().getX() + i, pos.get().getY()))
                                     .collect(Collectors.toList());

        return tmp;
    }

    @Override
    public boolean isPlaced() {
        return this.placed;
    }

    @Override
    public Optional<Point2d> getPosition() {
        return this.pos;
    }

    @Override
    public List<Point2d> getProjectionPoints(final Point2d point) {
        List<Point2d> points = IntStream.range(point.getX(), (point.getX() + this.getSize()))
                                        .mapToObj(x -> new Point2dImpl(x, point.getY()))
                                        .collect(Collectors.toList());

        return points;
    }

    @Override
    public boolean isSunk() {
        if (this.hitPoints.size() >= this.getSize()) {
            return true;
        }

        return false;
    }

    private static class AirCarrier extends AbstractShip {
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


    private static class Battleship extends AbstractShip {
        private static final long serialVersionUID = 8043537272411631772L;

        public Battleship() {
            super();
        }

        @SuppressWarnings("unused")
        public Battleship(final Point2d start) {
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


    private static class Cruiser extends AbstractShip {
        private static final long serialVersionUID = -5532557604937632667L;

        public Cruiser() {
            super();
        }

        @SuppressWarnings("unused")
        public Cruiser(final Point2d start) {
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


    private static class Submarine extends AbstractShip {
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

