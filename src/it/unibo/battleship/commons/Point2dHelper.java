package it.unibo.battleship.commons;

/**
 * Helper class for Point2d
 *
 * @author fabio.urbini
 */
public final class Point2dHelper {
   private Point2dHelper() {
   }

   // TODO: test -> method results must match

   /**
    * Returns an index based on the point and the boundary.
    * It starts from 0.
    *
    * @param point    {@link Point2d}
    * @param boundary {@link Boundary}
    * @return an index based on the point and the boundary
    */
   public static int getIndexByPoint2d(final Point2d point, final Boundary boundary) {

      // TODO: point must be within the boundary limit
      // boundary(10,10) -> max point 9,9
      return boundary.getColumnsNumber() * point.getY() + point.getX();
   }

   /**
    * Gets a Point2d based on the inserted index and the boundary
    *
    * @param zeroBasedIndex zero based index, starts from 0
    * @param boundary       boundary of the field
    * @return a Point2d
    */
   public static Point2d getPoint2dByIndex(final int zeroBasedIndex, final Boundary boundary) {

      // TODO: index must be within the boundary limit
      // boundary(10,10) -> max index = 10*10 - 1
      final int y = zeroBasedIndex / boundary.getColumnsNumber();
      final int x = zeroBasedIndex % boundary.getColumnsNumber();

      return new Point2dImpl(x, y);
   }
}

