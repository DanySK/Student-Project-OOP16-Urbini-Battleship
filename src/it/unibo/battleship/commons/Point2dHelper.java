package it.unibo.battleship.commons;

import static it.unibo.battleship.commons.GlobalProperties.POINT_NOT_WITHIN_LIMITS;
import static it.unibo.battleship.commons.GlobalProperties.INDEX_NOT_WITHIN_LIMITS;

/**
 * Helper class for Point2d
 *
 * @author fabio.urbini
 */
public final class Point2dHelper {
  private Point2dHelper() { }

  /**
   * Returns an index based on the point and the boundary. It starts from 0.
   * It will be using the current Ruleset Boundary
   * @param point
   *          {@link Point2d}
   * @return an index based on the point and the boundary
   */
  public static int getIndex(final Point2d point) {
    checkPointWithinBoundaryLimits(point);
    return Ruleset.BOUNDARY.getColumnnsCount() * point.getY() + point.getX();
  }

  /**
   * Creates a Point2d based on the index and the current Boundary
   * set in the Ruleset
   *
   * @param zeroBasedIndex
   *          zero based index, starts from 0
   * @return a Point2d
   */
  public static Point2d createPoint2d(final int zeroBasedIndex) {
    checkIndexWithinBoundaryLimits(zeroBasedIndex);
    final int y = zeroBasedIndex / Ruleset.BOUNDARY.getColumnnsCount();
    final int x = zeroBasedIndex % Ruleset.BOUNDARY.getColumnnsCount();

    return new Point2dImpl(x, y);
  }

  /**
   * Checks if the point is within the boundary limits of the
   * current {@link Boundary} (in {@link Ruleset}).
   * Throws an IllegalArgumentException if the point is not
   * within the limits.
   * @param point any {@link Point2d}
   */
  public static void checkPointWithinBoundaryLimits(final Point2d point)
      throws IllegalArgumentException {
    if( !Ruleset.isPointWithinLimits(point) ) {
      throw new IllegalArgumentException(POINT_NOT_WITHIN_LIMITS);
    }
  }

  /**
   * Checks if the index is within the boundary limit.
   * If not, an {@link IllegalArgumentException} will be thrown.
   * @param zeroBasedIndex zero based index.
   */
  public static void checkIndexWithinBoundaryLimits(final int zeroBasedIndex)
      throws IllegalArgumentException {
    if (zeroBasedIndex >= Ruleset.BOUNDARY.getSize()) {
      throw new IllegalArgumentException(INDEX_NOT_WITHIN_LIMITS);
    }
  }
}
