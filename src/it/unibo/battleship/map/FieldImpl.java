package it.unibo.battleship.map;

import com.google.common.base.Objects;
import it.unibo.battleship.commons.*;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.ships.ShipDirection;
import it.unibo.battleship.shots.Shot;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Implementation of a Field.
 * @author fabio.urbini
 */
@Immutable
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
    this.fieldMatrix = new FieldMatrix(boundary);
  }

  public static FieldImpl createField(final Boundary boundary) {
    if ((boundary.getColumnsCount() < 0) || (boundary.getRowsCount() < 0)) {
      throw new IllegalArgumentException(
          GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
    }
    return new FieldImpl(boundary);
  }

  @Override
  public void placeShip(final Ship ship, final Point2d point,
      final ShipDirection direction) {
    this.checkShipPlacement(ship, point);
    ship.place(point, direction);

    for (final Point2d p : ship.getAllPositions()) {
      this.fieldMatrix.getAt(p).placeShip(ship);
    }
  }

  @Override
  public void placeShip(final Ship ship, final Point2d point) {
    this.placeShip(ship, point, ShipDirection.EAST);
  }

  @Override
  public void updateStateWithShot(final Shot shot) {
    Point2dHelper.checkPointWithinBoundaryLimits(shot.getPoint());

    final Point2d p = shot.getPoint();
    this.fieldMatrix.getAt(p).shoot(shot);
  }

  private void checkShipPlacement(final Ship ship, final Point2d point) {
    Point2dHelper.checkPointWithinBoundaryLimits(point);
    this.checkShipPlaceable(ship, point);
    this.checkShipWithinLimits(ship, point);
  }

  private void checkShipWithinLimits(final Ship ship, final Point2d point) {
    if (!Ruleset.isShipWithinLimits(ship, point)) {
      throw new IllegalArgumentException(
          // TODO: add more details
          GlobalProperties.SHIP_NOT_WITHIN_LIMITS
      );
    }
  }

  private void checkShipPlaceable(final Ship ship, final Point2d point) {
    if (!this.isShipPlaceable(ship, point)) {
      throw new IllegalArgumentException(
          // ToDO: add more details to the exception
          GlobalProperties.FIELD_CELLS_NOT_EMPTY
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
    return ship
        .getProjectionPoints(point)
        .stream()
        .allMatch(p -> {
          Point2dHelper.checkPointWithinBoundaryLimits(p);
          return this.fieldMatrix.getAt(p).isEmpty();
        });
  }

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
        this.boundary.getColumnsCount() + " columns } ";
  }

  private static class FieldMatrix {
    private final FieldCell[] fieldCells;
    private final Boundary boundary;

    public FieldMatrix(final Boundary boundary) {
      this.boundary = boundary;
      this.fieldCells = new FieldCell[boundary.getSize()];
      this.initialize();
    }

    public FieldCell getAt(final int zeroBasedIndex) {
      return this.fieldCells[zeroBasedIndex];
    }

    public FieldCell getAt(final Point2d point) {
      final int idx = Point2dHelper.getIndex(point);
      return this.getAt(idx);
    }

    public FieldCell[][] getMatrix() {
      final int rows = this.boundary.getRowsCount();
      final int cols = this.boundary.getColumnsCount();
      final FieldCell[][] matrix = new FieldCell[rows][cols];

      for (int row = 0; row < rows; row++) {
        for (int column = 0; column < cols; column++) {
          final Point2d p = new Point2dImpl(column, row);
          final int idx = Point2dHelper.getIndex(p);
          matrix[row][column] = this.fieldCells[idx];
        }
      }
      return matrix;
    }

    private void initialize() {
      for (int i = 0; i < this.boundary.getSize(); i++) {
        this.fieldCells[i] = new FieldCellImpl();
      }
    }
  }
}
