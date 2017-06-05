package it.unibo.battleship.ships;

import com.google.common.base.Objects;
import it.unibo.battleship.common.*;
import it.unibo.battleship.shots.Shot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * come mantenere size final e che abbia quel valore alla scelta del sottotipo?
 * 
 * 
 * 
 */
public abstract class AbstractShip implements Ship {
    // DIREZIONE DELLA NAVE AL MOMENTO A DESTRA, di default
    private ShipDirection shipDirection;
    private Optional<Point2d> pos; // final - OPTIONAL? 
    private boolean placed;
    private final List<Point2d> hitPoints; // array delle posizioni colpite

    protected AbstractShip(final Point2d start) {
        this();
        this.pos = Optional.of(start);
        this.placed = true;
    }
    
    private AbstractShip() {
    	this.pos = Optional.empty();
        this.placed = false;
        this.shipDirection = ShipDirection.EAST;
        hitPoints = new ArrayList<Point2d>();
    }
    
    @Override
    public boolean isSunk() {
		if (hitPoints.size() == this.getSize() ) {
//		    System.out.println(toString() + ": nave affondata!!");
		    return true;
		}
		return false;
    }

	// METODO OPTIONAL - pu� ritornare null
    @Override
    public Optional<Point2d> getPosition() {
        return this.pos;
    }

    // Fare metodo cached 
    // RESTITUISCE I PUNTI OCCUPATI DALLA NAVE
    // OPTIONAL!!!!
    @Override
    public List<Point2d> getAllPositions() {
        List<Point2d> tmp = new ArrayList<>();
        for (int i = 0; i < this.getSize() ; i++) {
            tmp.add(new Point2dImpl(pos.get().getX() + i, pos.get().getY()));
        }

        return tmp;
    }
    
    // restituisce tutti i punti occupati virtualmente
    @Override
    public List<Point2d> getProjectionPoints(final Point2d point) {
        // DIREZIONE EST (x++)
        List<Point2d> points = new ArrayList<>();
        
        for (int x = point.getX(); x < (point.getX() + this.getSize() ); x++) {
            points.add(new Point2dImpl(x, point.getY()));
        }
        
        return points;
    }

    @Override
    public boolean containsPosition(final Point2d point) {
        for (Point2d p : this.getAllPositions()) {
            // SERVE metodo EQUALS
            if ((p.getX() == point.getX() && p.getY() == point.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void place(final Point2d start) {
        //  DIREZIONE DATA IN MODO STANDARD
        // Fare un controllo sulle navi gi� presenti da qui? 
        if (!placed) {
            this.pos = Optional.of(start);
            placed = true;
        } else {
            this.pos = Optional.of(start);
        }
    }

    @Override
    public boolean isPlaced() {
        return this.placed;
    }

    @Override
    public boolean shoot(final Shot shot) {
        // Controllo : gi� colpita?
        // shot non valido? 
        // Metodo valido al momento solo per una casella
        
        if (containsPosition(shot.getPoint())) {
            hitPoints.add(shot.getPoint());
            // AFFONDATA? 
            if (isSunk() ){
            	System.out.println(toString() + " affondato!");
            }
            return true;
        }
        
        return false;
    }

    @Override
    public void resetPlacement() {
        this.placed = false;
        pos = null; // USARE OPTIONAL?
    }
    
    public abstract String toString(); 

    /*
     * FACTORY METHODS
     */
    
    /**
     * Creates a ship 
     * @param size size of the ship. The value must be 
     * between 0 and {@see GlobalProperties.MAX_SIZE}
     * @return
     */
    public static Ship createShip(final int size) {
    	// TODO: MAGIC NUMBER 
    	if (size < 0 || size > GlobalProperties.MAX_SIZE ) {
    		throw new IllegalArgumentException("Valore non valido");
    	}

    	switch(size) {
    	case GlobalProperties.SUBMARINE_SIZE: return new Submarine();
    	case GlobalProperties.CRUISER_SIZE : return new Cruiser();
    	case GlobalProperties.BATTLESHIP_SIZE : return new Battleship();
    	case GlobalProperties.AIR_CARRIER_SIZE : return new AirCarrier();
    	default : throw new IllegalArgumentException("Valore non valido");
    	}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractShip that = (AbstractShip) o;

        return Objects.equal(this.shipDirection, that.shipDirection) &&
                Objects.equal(this.pos, that.pos) &&
                Objects.equal(this.placed, that.placed) &&
                Objects.equal(this.hitPoints, that.hitPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(shipDirection, pos, placed, hitPoints);
    }

    private static class Submarine extends AbstractShip {

        /**
		 * 
		 */
		private static final long serialVersionUID = -2784639518931814680L;

		Submarine(Point2d start) {
            super(start);
        }

        Submarine() {
            super();
        }

		@Override
		public int getSize() {
			return GlobalProperties.ShipRules.SUBMARINE.getSize();
		}

		@Override
		public String toString() {
			return GlobalProperties.ShipRules.SUBMARINE.toString();
		}

    }

    private static class Cruiser extends AbstractShip {

        /**
		 * 
		 */
		private static final long serialVersionUID = -5532557604937632667L;

		public Cruiser(Point2d start) {
            super(start);
        }

        public Cruiser() {
            super();
        }

		@Override
		public int getSize() {
			return GlobalProperties.ShipRules.CRUISER.getSize();
		}

		@Override
		public String toString() {
			return GlobalProperties.ShipRules.CRUISER.toString();
		}
    }

    private static class Battleship extends AbstractShip {
        
        /**
		 * 
		 */
		private static final long serialVersionUID = 8043537272411631772L;

		public Battleship(Point2d start) {
            super(start);
        }
        
        public Battleship() {
            super();
        }

		@Override
		public int getSize() {
			return GlobalProperties.ShipRules.BATTLESHIP.getSize();
		}

		@Override
		public String toString() {
			return GlobalProperties.ShipRules.BATTLESHIP.toString();
		}
    }

    private static class AirCarrier extends AbstractShip {

    	/**
		 * 
		 */
		private static final long serialVersionUID = -8323321815851042898L;

		AirCarrier(Point2d start) {
    		super(start);
    	}
    	
    	AirCarrier() {
    		super();
    	}
    	
		@Override
		public int getSize() {
			return GlobalProperties.AIR_CARRIER_SIZE;
		}

		@Override
		public String toString() {
			return GlobalProperties.ShipRules.AIR_CARRIER.toString();
		}
    	
    }
}

