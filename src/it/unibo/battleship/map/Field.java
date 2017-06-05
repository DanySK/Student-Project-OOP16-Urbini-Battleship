package it.unibo.battleship.map;

import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.common.Ruleset;
import it.unibo.battleship.common.ShotImpl;
import it.unibo.battleship.ships.AbstractShip;

// TODO: javadoc
/**
 * Represents the field
 * @author fabio.urbini
 *
 */
public final class Field {
    private final FieldCell[][] matrix;
    private final int rows;
    private final int columns;

    public Field(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new FieldCell[rows][columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                matrix[i][j] = new FieldCellImpl();
            }
        }
    }

    public void updateStateWithShot(final ShotImpl shot) {
        Point2d p = shot.getPoint();
        int x = p.getX();
        int y = p.getY();

        this.matrix[y][x].tryShoot(shot);
    }

    public void placeShip(final AbstractShip ship, final Point2d point) {
        validateShipPlacement(ship, point);

        ship.place(point);
        for (Point2d p : ship.getAllPositions()) {
            this.matrix[p.getY()][p.getX()].placeShip(ship);
        }
    }

    private void validateShipPlacement(final AbstractShip ship, final Point2d point) {
      if ( !Ruleset.isPointWithinLimits(point) ) {
          throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS_EX);
      }

      if ( !isShipPlaceable(ship, point)) {
          throw new IllegalArgumentException(GlobalProperties.FIELD_CELLS_NOT_EMPTY);
      }

      if ( !Ruleset.isShipWithinLimits(ship, point)) {
          throw new IllegalArgumentException(GlobalProperties.SHIP_NOT_WITHIN_LIMITS);
      }
    }

    // TODO: move to an utility class
    public char[][] getMatrix() {
        char[][] chars = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (matrix[i][j].isEmpty()) {
                    chars[i][j] = 'E';
                }
                if (matrix[i][j].isPresent()) {
                    chars[i][j] = '*';
                }
                if (matrix[i][j].isMissed()) {
                    chars[i][j] = 'M';
                }
            }
        }

        return chars;
    }

    public boolean isShipSunk(final AbstractShip s) {
        return s.isSunk();
    }

    private boolean isShipPlaceable(final AbstractShip ship, final Point2d point) {
        for (Point2d p : ship.getProjectionPoints(point)) {
            if ( !this.matrix[p.getX()][p.getY()].isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
