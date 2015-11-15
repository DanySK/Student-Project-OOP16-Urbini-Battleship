package it.unibo.battleship.model;

import it.unibo.battleship.common.Cell;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.common.State;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShip implements Ship {

//    private final int dimension;
    private Point2d startingPosition;
    private Point2d endingPosition;
    private boolean sank;
    private boolean placed;
    private List<Cell> cells;

    public AbstractShip(final Point2d startingPos, final Point2d endingPos) {
        // Controllare che la dimensione sia ok
        // Controllare che le posizioni siano permesse DALLA MAPPA
        // Il controllo di altre navi viene fatto invece dalla flotta
//        this.dimension = dimension;
        this.startingPosition = startingPos;
        this.endingPosition = endingPos;
        this.placed = true;
        this.sank = false;
        this.cells = new ArrayList<>();
    }

    public abstract int getDimension();

    public Point2d getStartingPosition() {
        return this.startingPosition;
    }

    public Point2d getEndingPosition() {
        return this.endingPosition;
    }

    public boolean isSank() {
        return this.sank;
    }

    public boolean isPlaced() {
        return this.placed;
    }

    public boolean tryHit(Point2d point) {
        // Cambia lo stato delle celle
        // Usare uno stream
        int hit = 0;
        for (Cell c : this.cells) {
            // EQUALS
            if (c.getCurrentPoint().equals(point)) {
                c.setState(State.hit);
            }
            // Se la cella è stata colpita, aumenta il conteggio
            if (c.getState() == State.hit) {
                hit++;
            }
        }
        // Nave affondata? -> set degli stati
        // Template method usato
        if (hit == this.getDimension()) {
            for (Cell c : this.cells) {
                c.setState(State.sank);
            }
        }

        return false;
    }

    public boolean move(final Point2d startingPoint, final Point2d endingPoint) {
        this.startingPosition = startingPoint;
        this.endingPosition = endingPoint;
        return true;
    }

    public List<Cell> getCellsList() {
        // PASSARE UNA COPIA
        return this.cells;
    }

}
