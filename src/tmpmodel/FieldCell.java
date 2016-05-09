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
    public void placeShip(final AbstractShip s) {
        this.ship = Optional.of(s);
        this.currentState = State.PRESENT;
    }

    public void tryShoot(final Shot s ) {
        switch (currentState) {
            case WATER: this.currentState = State.MISSED; break;
            case MISSED : break; // ECCEZIONE
            case PRESENT : this.ship.get().shoot(s); break; // POSSIBILE ERRORE OPTIONAL?
            default : break; // ECCEZIONE 

        }
    }

    // Oppure fare isWater, isMissed... 
//    public State getCurrentState() {
//        return this.currentState;
//    }

    public boolean isMissed() {
        return this.currentState == State.MISSED;
    }

//    public boolean isWater() {
//        return State.WATER == this.currentState;
//    }

    public boolean isEmpty() {
        return this.currentState == State.WATER;
    }
    
    public boolean isPresent() {
        return this.currentState == State.PRESENT;
    }
}
