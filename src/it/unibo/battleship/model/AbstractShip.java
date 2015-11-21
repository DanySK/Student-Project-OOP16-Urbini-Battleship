package it.unibo.battleship.model;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.common.Square;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.common.SquareImpl;
import it.unibo.battleship.common.State;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShip implements Ship {

//    private final int dimension;
    private Point2d startingPosition;
    private Point2d endingPosition;
    private boolean sunk;
    private boolean placed;
    private List<Square> squares;

    public AbstractShip(final Point2d startingPos, final Point2d endingPos) {
        // Controllare che la dimensione sia ok
        // Controllare che le posizioni siano permesse DALLA MAPPA
        // Il controllo di altre navi viene fatto invece dalla flotta
//        this.dimension = dimension;
        this.startingPosition = startingPos;
        this.endingPosition = endingPos;
        this.placed = true;
        this.sunk = false;
        this.squares = new ArrayList<>();
        setSquares();
    }

    private void setSquares() {
        Boundary tmp = new Boundary(10,10);
        for (int i = startingPosition.getIndex(); i < endingPosition.getIndex() + 1; i++) {
            this.squares.add(new SquareImpl(new Point2d(i, tmp), State.PRESENT));
            
        }
    }
    //public abstract String getType();
    public String getType() {
        return this.getClass().getSimpleName().toString();
    }

    public Point2d getStartingPosition() {
        return this.startingPosition;
    }

    public Point2d getEndingPosition() {
        return this.endingPosition;
    }

    public boolean isSunk() {
        return this.sunk;
    }

    public boolean isPlaced() {
        return this.placed;
    }

    public boolean tryHit(Point2d point) {
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

    public boolean move(final Point2d startingPoint, final Point2d endingPoint) {
        this.startingPosition = startingPoint;
        this.endingPosition = endingPoint;
        return true;
    }

    public List<Square> getSquareList() {
        // PASSARE UNA COPIA
        return this.squares;
    }

}
