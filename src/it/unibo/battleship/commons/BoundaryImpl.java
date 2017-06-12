package it.unibo.battleship.commons;

import com.google.common.base.Objects;

/**
 * Implementation of Boundary
 * {@see Boundary}
 */
public final class BoundaryImpl implements Boundary {
    private final int columnsCount;
    private final int rowsCount;

    private BoundaryImpl(final int columnsCount, final int verticalBound) {
        this.columnsCount = columnsCount;
        this.rowsCount    = verticalBound;
    }

    /**
     * @param columnsCount Horizontal bound
     * @param rowsCount Vertical bound
     */
    public static BoundaryImpl createBoundary(final int columnsCount, final int rowsCount) {
        if ((columnsCount < 0) || (rowsCount < 0)) {
            throw new IllegalArgumentException(GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
        }

        return new BoundaryImpl(columnsCount, rowsCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        final BoundaryImpl that = (BoundaryImpl) o;

        return Objects.equal(this.columnsCount, that.columnsCount) && Objects.equal(this.rowsCount, that.rowsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(columnsCount, rowsCount);
    }

    @Override
    public String toString() {
        return " x : " + this.columnsCount + " y : " + rowsCount;
    }

    @Override
    public final int getColumnsNumber() {
        return this.columnsCount;
    }

    @Override
    public final int getRowsNumber() {
        return this.rowsCount;
    }
}

