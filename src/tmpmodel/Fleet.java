package tmpmodel;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private final List<AbstractShip> ships;
    private boolean isSunk;
    
    public Fleet() {
        this.ships = new ArrayList<>();
        isSunk = false;
    }
    
    public List<AbstractShip> getAllShips() {
        // Usare la copia non modificabile, andare a rivedere 
        return ships;
    }
    
    public void addShip(AbstractShip s) {
        this.ships.add(s);
    }
    
    public boolean isSunk() {
        return this.isSunk;
    }
}
