package tmpmodel;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private final List<AbstractShip> ships;
    private boolean sunk;
    private boolean ready;
    
    public Fleet() {
        this.ships = new ArrayList<>();
        sunk = false;
        ready = false;
    }
    
    public List<AbstractShip> getAllShips() {
        // Usare la copia non modificabile, andare a rivedere 
        return this.ships;
    }
    
    public void addShip(AbstractShip s) {
        this.ships.add(s);
    }
    
    public boolean isSunk() {
        return this.sunk;
    }
    
    public boolean isReady() {
        //return this.ready;
        
        for (final AbstractShip s : this.ships ) {
            if (!s.isPlaced()) {
                return false;
            }
        }
        
        return true;
    }
}
