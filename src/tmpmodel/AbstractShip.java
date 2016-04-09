package tmpmodel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShip {
    private final Point2d pos;
    private final int size; 
    private boolean sunk;

    public AbstractShip(final Point2d start, final int size) {
        this.pos = start;
        this.size = size;
        this.sunk = false;
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
    
    public boolean containsPosition(final Point2d p) {
        return getAllPositions().contains(p);
    }
    
    // Metodo da ridefinire : metodo che restituisce tutte le posizioni occupate
    
    
    
    
    
    public static class Submarine extends AbstractShip {

        public Submarine(Point2d start, int size) {
            super(start, size);
            
        }
        
    }
}
