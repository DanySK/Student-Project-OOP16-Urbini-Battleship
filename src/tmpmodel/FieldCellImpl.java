package tmpmodel;

import java.util.Optional;

public class FieldCellImpl implements FieldCell {
    private State currentState;
    private Optional<AbstractShip> ship;

    // DOPO tryShoot potrebbe esser restituito lo stato della cella
    // Questo però renderebbe visibile la struttura della classe

    public FieldCellImpl() {
        this.currentState = State.WATER;
        this.ship = Optional.empty();
    }

    // tryPlaceShip -> restituire eccezione
    /* (non-Javadoc)
	 * @see tmpmodel.FieldCell#placeShip(tmpmodel.AbstractShip)
	 */
    @Override
	public void placeShip(final AbstractShip s) {
        this.ship = Optional.of(s);
        this.currentState = State.PRESENT;
    }

    /* (non-Javadoc)
	 * @see tmpmodel.FieldCell#tryShoot(tmpmodel.Shot)
	 */
    @Override
	public void tryShoot(final Shot s ) {
        switch (currentState) {
            case WATER: this.currentState = State.MISSED; break;
            case MISSED : break; // ECCEZIONE
            case PRESENT : this.ship.get().shoot(s); break; // POSSIBILE ERRORE OPTIONAL?
            default : break; // ECCEZIONE 

        }
    }
    
    public Optional<AbstractShip> getShip() {
    	return this.ship;
    }
    /* (non-Javadoc)
	 * @see tmpmodel.FieldCell#isMissed()
	 */
    @Override
	public boolean isMissed() {
        return this.currentState == State.MISSED;
    }

    /* (non-Javadoc)
	 * @see tmpmodel.FieldCell#isEmpty()
	 */
    @Override
	public boolean isEmpty() {
        return this.currentState == State.WATER;
    }
    
    /* (non-Javadoc)
	 * @see tmpmodel.FieldCell#isPresent()
	 */
    @Override
	public boolean isPresent() {
        return this.currentState == State.PRESENT;
    }
}
