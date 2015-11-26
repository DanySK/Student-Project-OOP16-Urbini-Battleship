package it.unibo.battleship.model.interfaces;

import it.unibo.battleship.model.common.Boundary;
import it.unibo.battleship.model.common.PointImpl;
import it.unibo.battleship.model.common.Square;
import it.unibo.battleship.model.common.SquareImpl;
import it.unibo.battleship.model.common.State;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShip implements Ship {

//    private final int dimension;
    private PointImpl startingPosition;
    private PointImpl endingPosition;
    private boolean sunk;
    private boolean placed;
    private List<Square> squares;
    private final int dimension;

    public AbstractShip(final int dimension) {
        this.dimension = dimension;
        setPrivateFields(dimension, false);
    }
    public AbstractShip(final int dimension, final PointImpl startingPos,
            final PointImpl endingPos) {
        // Controllare che la dimensione sia ok
        // Controllare che le posizioni siano permesse DALLA MAPPA
        // Il controllo di altre navi viene fatto invece dalla flotta
//        this.dimension = dimension;
        this.startingPosition = startingPos;
        this.endingPosition = endingPos;
        this.dimension = dimension;
        
        setPrivateFields(dimension, true);
        this.squares = new ArrayList<>();
        setSquares();
    }

    private void setPrivateFields(int dimension, boolean placed) {
        this.sunk = false;
        this.placed = placed;
    }
    
    private void setSquares() {
        Boundary tmp = new Boundary(10,10);
        for (int i = startingPosition.getIndex(); i < endingPosition.getIndex() + 1; i++) {
            this.squares.add(new SquareImpl(new PointImpl(i, tmp), State.PRESENT));
        }
    }
    
    public final int getDimension() {
        return this.dimension;
    }

    public final String getType() {
        return this.getClass().getSimpleName().toString();
    }

    @Override
    public final PointImpl getStartingPosition() {
        return this.startingPosition;
    }

    @Override
    public final PointImpl getEndingPosition() {
        return this.endingPosition;
    }

    @Override
    public final boolean isSunk() {
        return this.sunk;
    }

    @Override
    public final boolean isPlaced() {
        return this.placed;
    }

    @Override
    public final boolean tryHit(PointImpl point) {
        // Cambia lo stato delle celle
        // Usare uno stream
        int hit = 0;
        boolean retVal = false;
        
        for (Square c : this.squares) {
            // EQUALS
            if (c.getCurrentPoint().equals(point)) {
                c.setState(State.HIT);
                retVal = true;
            }
            // Se la cella è stata colpita, aumenta il conteggio
            if (c.getState() == State.HIT) {
                hit++;
            }
        }
        // Nave affondata? -> set degli stati
        // Template method usato
        if (hit == this.getDimension()) {
            for (final Square c : this.squares) {
                c.setState(State.SUNK);
            }
            this.sunk = true;
        }

        return retVal;
    }

    public boolean move(final PointImpl startingPoint, final PointImpl endingPoint) {
        this.startingPosition = startingPoint;
        this.endingPosition = endingPoint;
        return true;
    }

    public List<Square> getSquareList() {
        // PASSARE UNA COPIA
        return this.squares;
    }

}
