package tmpmodel;

public class FieldCell {
    private State currentState;

    public FieldCell(final State currentState) {
        this.currentState = currentState;
    }

    public void setState(final State state) {
        this.currentState = state;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}
