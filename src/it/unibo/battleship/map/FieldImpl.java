package it.unibo.battleship.map;

import it.unibo.battleship.common.*;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;

import java.util.Arrays;

// TODO: javadoc

public final class FieldImpl implements Field {
    private final FieldCell[][] fieldCells;
    private final int rows;
    private final int columns;

    private FieldImpl(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        fieldCells = new FieldCell[rows][columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                fieldCells[i][j] = new FieldCellImpl();
            }
        }
    }

    public static FieldImpl createField(final Boundary boundary) {
        if (boundary.getHorizontalBound() < 0 || boundary.getVerticalBound() < 0) {
            throw new IllegalArgumentException(GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
        }

        return new FieldImpl(boundary.getVerticalBound(), boundary.getHorizontalBound());
    }

    @Override
    public void updateStateWithShot(final Shot shot) {
        if (!Ruleset.isPointWithinLimits(shot.getPoint())) {
            throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS);

        }

        final Point2d p = shot.getPoint();
        int x = p.getX();
        int y = p.getY();

        this.fieldCells[y][x].shoot(shot);
    }

    @Override
    public void placeShip(final Ship ship, final Point2d point) {
        validateShipPlacement(ship, point);

        ship.place(point);
        for (Point2d p : ship.getAllPositions()) {
            this.fieldCells[p.getY()][p.getX()].placeShip(ship);
        }
    }

    @Override
    public Boundary getBoundary() {
        return BoundaryImpl.createBoundary(rows, columns);
    }

    public FieldCell[][] getFieldCells() {
        return Arrays.copyOf(this.fieldCells, this.fieldCells.length);
    }

    private void validateShipPlacement(final Ship ship, final Point2d point) {
      if ( !Ruleset.isPointWithinLimits(point) ) {
          throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS);
      }

      if ( !isShipPlaceable(ship, point)) {
          throw new IllegalArgumentException(GlobalProperties.FIELD_CELLS_NOT_EMPTY);
      }

      if ( !Ruleset.isShipWithinLimits(ship, point)) {
          throw new IllegalArgumentException(GlobalProperties.SHIP_NOT_WITHIN_LIMITS);
      }
    }

    private boolean isShipPlaceable(final Ship ship, final Point2d point) {
        return ship.getProjectionPoints(point)
                .stream()
                .allMatch(p -> this.fieldCells[p.getX()][p.getY()].isEmpty());
    }
}
