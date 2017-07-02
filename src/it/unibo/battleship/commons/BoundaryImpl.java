package it.unibo.battleship.commons;

import com.google.common.base.Objects;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Implementation of {@link Boundary}
 */
@Immutable
public final class BoundaryImpl implements Boundary {
  private static final long serialVersionUID = -5121855953223117935L;
  private final int columnsCount;
  private final int rowsCount;

  private BoundaryImpl(final int columnsNumber, final int rowsNumber) {
    this.columnsCount = columnsNumber;
    this.rowsCount = rowsNumber;
  }

  /**
   * Creates a boundary.
   *
   * @param columnsCount
   *          Columns count
   * @param rowsCount
   *          Rows count
   * @return a {@link BoundaryImpl}
   * @throws IllegalArgumentException if values are negative
   */
  public static BoundaryImpl createBoundary(final int columnsCount,
      final int rowsCount) {
    checkParametersValidity(columnsCount, rowsCount);
    return new BoundaryImpl(columnsCount, rowsCount);
  }

  private static void checkParametersValidity(final int columnsCount, final int rowsCount) {
    if (columnsCount < 0 || rowsCount < 0) {
      throw new IllegalArgumentException(
          GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
    }
  }

  @Override
  public int getColumnsCount() {
    return this.columnsCount;
  }

  @Override
  public int getRowsCount() {
    return this.rowsCount;
  }

  @Override
  public int getSize() {
    return this.rowsCount * this.columnsCount;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    final BoundaryImpl that = (BoundaryImpl) o;

    return Objects.equal(this.columnsCount, that.columnsCount)
        && Objects.equal(this.rowsCount, that.rowsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.columnsCount, this.rowsCount);
  }

  @Override
  public String toString() {
    return " x : " + this.columnsCount + " y : " + this.rowsCount;
  }


}
