package tmpmodel;

import java.util.ArrayList;
import java.util.List;

/*
 * come mantenere size final e che abbia quel valore alla scelta del sottotipo?
 * 
 * 
 * 
 */
public abstract class AbstractShip {
    // DIREZIONE DELLA NAVE AL MOMENTO A DESTRA, di default
    private ShipDirection shipDirection;
    private Point2d pos; // final - OPTIONAL? 
    private int size; // final
    private boolean sunk;
    private boolean placed;
    private final List<Point2d> hitPoints; // array delle posizioni colpite

    public AbstractShip(final Point2d start, final int size) {
        this(size);
        this.pos = start;
        this.placed = true;
    }

    public AbstractShip(final int size) {
        this();
        this.size = size;
    }
    
    private AbstractShip() {
        this.sunk = false; 
        this.placed = false;
        this.shipDirection = ShipDirection.East;
        hitPoints = new ArrayList<Point2d>();
    }

    public boolean isSunk() {
        return this.sunk;
    }

    public int getSize() {
        return this.size;
    }
    
    // METODO OPTIONAL - può ritornare null
    public Point2d getPos() {
        return this.pos;
    }

    // Fare metodo cached 
    // RESTITUISCE I PUNTI OCCUPATI DALLA NAVE
    public List<Point2d> getAllPositions() {
        List<Point2d> tmp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tmp.add(new Point2dImpl(pos.getX() + i, pos.getY()));
        }

        return tmp;
    }

    public boolean containsPosition(final Point2d point) {
        return getAllPositions().contains(point);
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

    public boolean shoot (Shot shot) {
        // Controllo : già colpita?
        // shot non valido? 
        // Metodo valido al momento solo per una casella
        
        if (containsPosition(shot.getPoint())) {
            hitPoints.add(shot.getPoint());
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
    
    public String getType() {
        return this.getClass().getSimpleName();
    }

    // CLASSI INNESTATE ***** DIPENDENZA DA RULESET *****
    public static class Submarine extends AbstractShip {

        public Submarine(Point2d start) {
            super(start, Ruleset.getSubmarineSize());
        }

        public Submarine() {
            super(Ruleset.getSubmarineSize());
        }

    }
    public static class Cruiser extends AbstractShip {

        public Cruiser(Point2d start) {
            super(start, Ruleset.getCruiserSize());
        }

        public Cruiser() {
            super(Ruleset.getCruiserSize());
        }
    }
}
