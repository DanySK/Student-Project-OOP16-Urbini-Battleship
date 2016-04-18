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

    public AbstractShip(final Point2d start, final int size) {
        this(size);
        this.pos = start;
        this.placed = true;
    }
    
    public AbstractShip(final int size) {
        this();
        this.size = size;
    }
    public AbstractShip() {
        this.sunk = false; 
        this.placed = false;
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
        // Fare un controllo sulle navi già presenti da qui? 
        this.pos = start;
    }
    
    public boolean isPlaced () {
        return this.placed;
    }
    
    
    
    
    
    // CLASSI INNESTATE
    public static class Submarine extends AbstractShip {

        public Submarine(Point2d start) {
            super(start, Ruleset.getSubmarineSize());
        }
        
        public Submarine() {
            super(Ruleset.getSubmarineSize());
        }
        
    }
    public static class Cruiser extends AbstractShip {
        
        public Cruiser(Point2d start, int size) {
            super(start, size);
        }
        
        public Cruiser() {
            // creare metodo in Ruleset
        }
    }
}
