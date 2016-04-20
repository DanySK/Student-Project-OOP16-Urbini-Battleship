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
    private Point2d pos; // final
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
        hitPoints = new ArrayList<Point2d>();
    }

    public boolean isSunk() {
        return this.sunk;
    }

    public int getSize() {
        return this.size;
    }

    public Point2d getPos() {
        return this.pos;
    }

    // Fare metodo cached 
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
        // Fare un controllo sulle navi gi� presenti da qui? 
        this.pos = start;
        placed = true;
    }

    public boolean isPlaced () {
        return this.placed;
    }

    public boolean shoot (Shot shot) {
        // Controllo : gi� colpita?
        // shot non valido? 
        // Metodo valido al momento solo per una casella
        
        if (containsPosition(shot.getPoint())) {
            hitPoints.add(shot.getPoint());
            return true;
        }
        
        return false;
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
