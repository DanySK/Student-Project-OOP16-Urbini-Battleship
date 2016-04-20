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
        // MODIFICARE
        // Usare la copia non modificabile, andare a rivedere 
        return this.ships;
    }

    public List<AbstractShip> getAllNonPlacedShips() {
        // Metodo da rivedere per il momento
        List<AbstractShip> npShips = new ArrayList<>();
        
        // ogni nave non piazzata viene aggiunta alla lista
        for (final AbstractShip s : this.getAllShips()) {
            if (!s.isPlaced()) {
                npShips.add(s);
            }
        }

        return npShips;
    }
    // public void placeNextShip(Type t) 


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
