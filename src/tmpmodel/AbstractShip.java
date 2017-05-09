package tmpmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

/*
 * come mantenere size final e che abbia quel valore alla scelta del sottotipo?
 * 
 * 
 * 
 */
public abstract class AbstractShip implements Serializable {
    // DIREZIONE DELLA NAVE AL MOMENTO A DESTRA, di default
    private ShipDirection shipDirection;
    private Point2d pos; // final - OPTIONAL? 
    private boolean placed;
    private final List<Point2d> hitPoints; // array delle posizioni colpite

    protected AbstractShip(final Point2d start) {
        this();
        this.pos = start;
        this.placed = true;
    }
    
    private AbstractShip() {
        this.placed = false;
        this.shipDirection = ShipDirection.East;
        hitPoints = new ArrayList<Point2d>();
    }

    public boolean isSunk() {
		if (hitPoints.size() == this.getSize() ) {
//		    System.out.println(toString() + ": nave affondata!!");
		    return true;
		}
		return false;
    }

    public abstract int getSize();
    
    // METODO OPTIONAL - può ritornare null
    public Point2d getPos() {
        return this.pos;
    }

    // Fare metodo cached 
    // RESTITUISCE I PUNTI OCCUPATI DALLA NAVE
    // OPTIONAL!!!!
    public List<Point2d> getAllPositions() {
        List<Point2d> tmp = new ArrayList<>();
        for (int i = 0; i < this.getSize() ; i++) {
            tmp.add(new Point2dImpl(pos.getX() + i, pos.getY()));
        }

        return tmp;
    }
    
    // restituisce tutti i punti occupati virtualmente
    public List<Point2d> getProjectionPoints (final Point2d point) {
        // DIREZIONE EST (x++)
        List<Point2d> points = new ArrayList<>();
        
        for (int x = point.getX(); x < (point.getX() + this.getSize() ); x++) {
            points.add(new Point2dImpl(x, point.getY()));
        }
        
        return points;
    }
//    public boolean containsPosition(final Point2d point) {
//        return getAllPositions().contains(point);
//    }

    public boolean containsPosition(final Point2d point) {
        for (Point2d p : this.getAllPositions()) {
            // SERVE metodo EQUALS
            if ((p.getX() == point.getX() && p.getY() == point.getY())) {
                return true;
            }
        }
        return false;
    }
    
    // Metodo da ridefinire : metodo che restituisce tutte le posizioni occupate

    public void place(final Point2d start) {
        //  DIREZIONE DATA IN MODO STANDARD
        // Fare un controllo sulle navi già presenti da qui? 
        if (!placed) {
            this.pos = start;
            placed = true;
        } else {
            this.pos = start;
        }
    }

    public boolean isPlaced () {
        return this.placed;
    }

    public boolean shoot (final Shot shot) {
        // Controllo : già colpita?
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
    
    // Può esser chiamato quando la flotta è pronta? 
    // Errore progettuale o dell'utente?
    // public o protected?
    public void reset() {
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
    public static AbstractShip createShip(final int size) {
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
    
    // CLASSI INNESTATE ***** DIPENDENZA DA RULESET *****
    private static class Submarine extends AbstractShip {

        Submarine(Point2d start) {
            super(start);
        }

        Submarine() {
            super();
        }

		@Override
		public int getSize() {
			return GlobalProperties.EnumNave.SUBMARINE.getSize();
		}

		@Override
		public String toString() {
			return GlobalProperties.EnumNave.SUBMARINE.toString();
		}

    }

    private static class Cruiser extends AbstractShip {

        public Cruiser(Point2d start) {
            super(start);
        }

        public Cruiser() {
            super();
        }

		@Override
		public int getSize() {
			return GlobalProperties.EnumNave.CRUISER.getSize();
		}

		@Override
		public String toString() {
			return GlobalProperties.EnumNave.CRUISER.toString();
		}
    }

    private static class Battleship extends AbstractShip {
        
        public Battleship(Point2d start) {
            super(start);
        }
        
        public Battleship() {
            super();
        }

		@Override
		public int getSize() {
			return GlobalProperties.EnumNave.BATTLESHIP.getSize();
		}

		@Override
		public String toString() {
			return GlobalProperties.EnumNave.BATTLESHIP.toString();
		}
    }

    private static class AirCarrier extends AbstractShip {

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
			return GlobalProperties.EnumNave.AIR_CARRIER.toString();
		}
    	
    }
}

