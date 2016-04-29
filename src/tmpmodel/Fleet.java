package tmpmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    // Ricevere una CLASSE potrebbe NON essere il modo corretto, RIGUARDARE
    public List<AbstractShip> getShipsByType(Class c) {
        List<AbstractShip> as = new ArrayList<AbstractShip>();
        
        for (AbstractShip ship : this.ships) {
            if ( c.getSimpleName().equals(ship.getClass().getSimpleName())) {
                if ( !ship.isPlaced()) {
                    as.add(ship);
                }
            }
        }
        
        return as;
    }

    // Ricevere una CLASSE potrebbe NON essere il modo corretto, RIGUARDARE
    public Optional<AbstractShip> getNextShipByType(Class c) {
        Optional<AbstractShip> s = Optional.empty();
        
        if (!getShipsByType(c).isEmpty()) {
            s = Optional.of(getShipsByType(c).get(0)); // PRESO IL PRIMO ELEMENTO
        }
        
        return s;
    }
    
    // RESTITUISCE la prossima nave NON PIAZZATA. Non la toglie dalla collezione
    public Optional<AbstractShip> getNextNonPlacedShip() {
        Optional<AbstractShip> s = Optional.empty();
        
        if (!getAllNonPlacedShips().isEmpty()) {
            s = Optional.of(getAllNonPlacedShips().get(0)); // PRESO IL PRIMO ELEMENTO
        }
        
        return s;
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
