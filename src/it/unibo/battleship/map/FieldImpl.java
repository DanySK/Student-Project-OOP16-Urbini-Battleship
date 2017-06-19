package it.unibo.battleship.map;

import java.util.Arrays;

import com.google.common.base.Objects;
import it.unibo.battleship.commons.*;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.ships.ShipDirection;
import it.unibo.battleship.shots.Shot;

//TODO: javadoc
public final class FieldImpl implements Field {
    private static final long serialVersionUID = 4129869956647585285L;
    /*
		 * The field cell matrix is like the first quadrant
		 * of the cartesian plane, seen upside down.
		 */
    private final FieldCell[][] fieldCells;
    private final int           rows;
    private final int           columns;

    private FieldImpl(final int rows, final int columns) {
        this.rows    = rows;
        this.columns = columns;
        this.fieldCells = new FieldCell[rows][columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.fieldCells[i][j] = new FieldCellImpl();
            }
        }
    }

    public static FieldImpl createField(final Boundary boundary) {
        if ((boundary.getColumnsNumber() < 0) || (boundary.getRowsNumber() < 0)) {
            throw new IllegalArgumentException(GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
        }

        return new FieldImpl(boundary.getColumnsNumber(), boundary.getRowsNumber());
    }

    @Override
    public void placeShip(final Ship ship,
                          final Point2d point,
                          final ShipDirection direction) {
        this.validateShipPlacement(ship, point);
        ship.place(point, direction);

        for (final Point2d p : ship.getAllPositions()) {
            this.fieldCells[p.getY()][p.getX()].placeShip(ship);
        }
    }

    @Override
    public void updateStateWithShot(final Shot shot) {
        if (!Ruleset.isPointWithinLimits(shot.getPoint())) {
            throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS);
        }

        final Point2d p      = shot.getPoint();
        this.fieldCells[p.getY()][p.getX()].shoot(shot);
    }

    private void validateShipPlacement(final Ship ship, final Point2d point) {
        if (!Ruleset.isPointWithinLimits(point)) {
            throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS);
        }

        if (!this.isShipPlaceable(ship, point)) {
            throw new IllegalArgumentException(GlobalProperties.FIELD_CELLS_NOT_EMPTY);
        }

        if (!Ruleset.isShipWithinLimits(ship, point)) {
            throw new IllegalArgumentException(GlobalProperties.SHIP_NOT_WITHIN_LIMITS);
        }
    }

    @Override
    public Boundary getBoundary() {
        return BoundaryImpl.createBoundary(this.rows, this.columns);
    }

    public FieldCell[][] getFieldCells() {
        return Arrays.copyOf(this.fieldCells, this.fieldCells.length);
    }

    private boolean isShipPlaceable(final Ship ship, final Point2d point) {
        return ship.getProjectionPoints(point)
                .stream()
                .allMatch(p -> {
                    return this.fieldCells[p.getY()][p.getX()].isEmpty();
                });
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        final FieldImpl that = (FieldImpl) o;

        return Objects.equal(this.fieldCells, that.fieldCells) &&
                Objects.equal(this.rows, that.rows) &&
                Objects.equal(this.columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.fieldCells, this.rows, this.columns);
    }

    @Override
    public String toString() {
        return "Field { " + this.rows
                + " rows }; { "
                + this.columns + " columns } ";
    }
}

