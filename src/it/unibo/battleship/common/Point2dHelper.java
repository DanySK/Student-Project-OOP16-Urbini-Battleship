/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.common;

import static it.unibo.battleship.common.GlobalProperties.POINT_NOT_WITHIN_LIMITS;
import static it.unibo.battleship.common.GlobalProperties.INDEX_NOT_WITHIN_LIMITS;

/**
 * Helper class for Point2d.
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
    return Ruleset.BOUNDARY.getColumnsCount() * point.getY() + point.getX();
  }

  /**
   * Creates a Point2d based on the index and the current Boundary
   * set in the Ruleset.
   *
   * @param zeroBasedIndex
   *          zero based index, starts from 0
   * @return a Point2d
   */
  public static Point2d createPoint2d(final int zeroBasedIndex) {
    checkIndexWithinBoundaryLimits(zeroBasedIndex);
    final int y = zeroBasedIndex / Ruleset.BOUNDARY.getColumnsCount();
    final int x = zeroBasedIndex % Ruleset.BOUNDARY.getColumnsCount();

    return new Point2dImpl(x, y);
  }

  /**
   * Checks if the point is within the boundary limits of the
   * current {@link Boundary} (in {@link Ruleset}).
   * @param point any {@link Point2d} within the boundary limit
   * @throws IllegalArgumentException if the point is not within the limits
   */
  public static void checkPointWithinBoundaryLimits(final Point2d point) {
    if( !Ruleset.isPointWithinLimits(point) ) {
      final String message = POINT_NOT_WITHIN_LIMITS + " x : " + point.getX() +
          " y : " + point.getY() + " -- Map SIZE : " + Ruleset.BOUNDARY;
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * Checks if the index is within the boundary limit.
   * @param zeroBasedIndex zero based index.
   * @throws IllegalArgumentException if the index is out of the boundary
   */
  public static void checkIndexWithinBoundaryLimits(final int zeroBasedIndex) {
    if (zeroBasedIndex >= Ruleset.BOUNDARY.getSize()) {
      final String message = INDEX_NOT_WITHIN_LIMITS + " index : "
          + zeroBasedIndex;
      throw new IllegalArgumentException(message);
    }
  }
}
