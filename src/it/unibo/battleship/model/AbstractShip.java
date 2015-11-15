package it.unibo.battleship.model;

import it.unibo.battleship.common.Cell;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.common.State;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShip implements Ship {

    private final int dimension;
    private Point2d startingPosition;
    private Point2d endingPosition;
    private boolean isSank;
    private boolean isPlaced;
    private List<Cell> cells;

    public AbstractShip(int dimension, Point2d startingPos, Point2d endingPos) {
        // Controllare che la dimensione sia ok
        // Controllare che le posizioni siano permesse DALLA MAPPA
        // Il controllo di altre navi viene fatto invece dalla flotta
        this.dimension = dimension;
        this.startingPosition = startingPos;
        this.endingPosition = endingPos;
        this.isPlaced = true;
        this.isSank = false;
        this.cells = new ArrayList<>();
    }

    /***
     * @see 
     */
    public final int getDimension() {
        return this.dimension;
    }

    public Point2d getStartingPoint() {
        return this.startingPosition;
    }

    public Point2d getEndingPoint() {
        return this.endingPosition;
    }

    public boolean isSank() {
        return this.isSank;
    }

    public boolean isPlaced() {
        return this.isPlaced;
    }

    public boolean tryHit(Point2d point) {
        // Cambia lo stato delle celle
        // Usare uno stream
        int hit = 0;
        for (Cell c : this.cells) {
            if (c.getState() == State.hit) {
                hit++;
            }
            // EQUALS
            if (c.getCurrentPoint().equals(point)) {
                c.setState(State.hit);
            }
        }
        // Nave affondata? -> set degli stati
        if (hit == this.dimension ) {
            for (Cell c : this.cells) {
                c.setState(State.sank);
            }
        }

        return false;
    }

    public boolean move(Point2d startingPoint, Point2d endingPoint) {
        this.startingPosition = startingPoint;
        this.endingPosition = endingPoint;
        return true;
    }

    public List<Cell> getCellsList() {
        // PASSARE UNA COPIA
        return this.cells;
    }

}
