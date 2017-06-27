package it.unibo.battleship.map;

import com.google.common.base.Objects;
import it.unibo.battleship.commons.*;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.ships.ShipDirection;
import it.unibo.battleship.shots.Shot;

/**
 * Implementation of a Field.
 * @author fabio.urbini
 */
public final class FieldImpl implements Field {
  private static final long serialVersionUID = 4129869956647585285L;
  /*
   * The field cell matrix is like the first quadrant of the cartesian plane,
   * seen upside down.
   */
  private final Boundary boundary;
  private final FieldMatrix fieldMatrix;


  private FieldImpl(final Boundary boundary) {
    this.boundary = boundary;
    fieldMatrix = new FieldMatrix(boundary);
  }

  public static FieldImpl createField(final Boundary boundary) {
    if ((boundary.getColumnnsCount() < 0) || (boundary.getRowsCount() < 0)) {
      throw new IllegalArgumentException(
          GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
    }
    return new FieldImpl(boundary);
  }

  @Override
  public void placeShip(final Ship ship, final Point2d point,
      final ShipDirection direction) {
    this.validateShipPlacement(ship, point);
    ship.place(point, direction);

    for (final Point2d p : ship.getAllPositions()) {
      this.fieldMatrix.getAt(p).placeShip(ship);
    }
  }

  @Override
  public void placeShip(final Ship ship, final Point2d point) {
    placeShip(ship, point, ShipDirection.EAST);
  }

  @Override
  public void updateStateWithShot(final Shot shot) {
    if (!Ruleset.isPointWithinLimits(shot.getPoint())) {
      throw new IllegalArgumentException(
          GlobalProperties.POINT_NOT_WITHIN_LIMITS);
    }

    final Point2d p = shot.getPoint();
    this.fieldMatrix.getAt(p).shoot(shot);
  }

  private void validateShipPlacement(final Ship ship, final Point2d point) {
    if (!Ruleset.isPointWithinLimits(point)) {
      throw new IllegalArgumentException(
          GlobalProperties.POINT_NOT_WITHIN_LIMITS
      );
    }

    if (!this.isShipPlaceable(ship, point)) {
      throw new IllegalArgumentException(
          GlobalProperties.FIELD_CELLS_NOT_EMPTY
      );
    }

    if (!Ruleset.isShipWithinLimits(ship, point)) {
      throw new IllegalArgumentException(
          GlobalProperties.SHIP_NOT_WITHIN_LIMITS
      );
    }
  }

  @Override
  public Boundary getBoundary() {
    return this.boundary;
  }

  public FieldCell[][] getFieldCells() {
    return this.fieldMatrix.getMatrix();
  }

  public boolean isShipPlaceable(final Ship ship, final Point2d point) {
    // TODO: throws ArrayIndexOutOfBoundExceptions, solve it by throwing an ex
    return ship
        .getProjectionPoints(point)
        .stream()
        .allMatch(p -> {
          if(!Ruleset.isPointWithinLimits(p)) {
            throw new IllegalArgumentException(
                GlobalProperties.POINT_NOT_WITHIN_LIMITS
            );
          }
          return this.fieldMatrix.getAt(p).isEmpty();
        });
  }

  /*
  TODO: equals, and hashCode -> rewrite
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || this.getClass() != o.getClass())
      return false;

    final FieldImpl that = (FieldImpl) o;

    return Objects.equal(this.fieldMatrix, that.fieldMatrix)
        && Objects.equal(this.boundary, that.boundary);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.fieldMatrix, this.boundary);
  }

  @Override
  public String toString() {
    return "Field { " + this.boundary.getRowsCount() + " rows }; { " +
        this.boundary.getColumnnsCount() + " columns } ";
  }

  private static class FieldMatrix {
    private final FieldCell[] fieldCells;
    private final Boundary boundary;

    public FieldMatrix(final Boundary boundary) {
      this.boundary = boundary;
      this.fieldCells = new FieldCell[boundary.getSize()];
      initialize();
    }

    public FieldCell getAt(final int zeroBasedIndex) {
      return this.fieldCells[zeroBasedIndex];
    }

    public FieldCell getAt(final Point2d point) {
      final int idx = Point2dHelper.getIndex(point, boundary);
      return getAt(idx);
    }

    public FieldCell[][] getMatrix() {
      final int rows = this.boundary.getRowsCount();
      final int cols = this.boundary.getColumnnsCount();
      final FieldCell[][] matrix = new FieldCell[rows][cols];

      for (int row = 0; row < rows; row++) {
        for (int column = 0; column < cols; column++) {
          final Point2d p = new Point2dImpl(row, column);
          final int idx = Point2dHelper.getIndex(p, this.boundary);
          matrix[row][column] = this.fieldCells[idx];
        }
      }
      return matrix;
    }
    private void initialize() {
      for (int i = 0; i < boundary.getSize(); i++) {
        this.fieldCells[i] = new FieldCellImpl();
      }
    }
  }
}
