package tmpmodel;

import java.util.Optional;

public class FieldCell {
    private State currentState;
    private Optional<AbstractShip> ship;

    // DOPO tryShoot potrebbe esser restituito lo stato della cella
    // Questo però renderebbe visibile la struttura della classe
    
    // water nel costruttore vuoto

    public FieldCell() {
        this.currentState = State.WATER;
        this.ship = Optional.empty();
    }

    // tryPlaceShip -> restituire eccezione
    public void placeShip( AbstractShip s) {
        this.ship = Optional.of(s);
        this.currentState = State.PRESENT;
    }

    public void tryShoot( Shot s ) {
        switch (currentState) {
            case WATER: this.currentState = State.MISSED; break;
            case MISSED : break;
            case PRESENT : this.ship.get().shoot(s); break;

        }
    }

    // Oppure fare isWater, isMissed... 
//    public State getCurrentState() {
//        return this.currentState;
//    }

    public boolean isMissed() {
        return State.MISSED == this.currentState;
    }

//    public boolean isWater() {
//        return State.WATER == this.currentState;
//    }

    public boolean isEmpty() {
        return (currentState == State.WATER);
    }
    
    public boolean isPresent() {
        return currentState == State.PRESENT;
    }
}
