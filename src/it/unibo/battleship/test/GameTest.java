package it.unibo.battleship.test;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.commons.BoundaryImpl;
import it.unibo.battleship.commons.Point2dHelper;
import it.unibo.battleship.commons.Ruleset;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * This test is designed to test some of the game features,
 * partially.
 * @author fabio.urbini
 */
public final class GameTest {
  /*
   * This test is designed to check if a game works properly It won't test all
   * classes designed.
   */
  // private fields

  @Before
  public void initialize() {

  }

  @Test
  public void assertShotsWithinLimits() {
    // fail("Not yet implemented");
    final Boundary bounds = BoundaryImpl.createBoundary(10, 10);
    final int maxIndex = bounds.getColumnnsCount() * bounds.getRowsCount();
    for (int i = 0; i < maxIndex; i++) {
      final Shot s = ShotImpl.createShot(Point2dHelper.createPoint2d(i));
      assertTrue(Ruleset.isPointWithinLimits(s.getPoint()));
    }
    // Try creating random shots

    /*
         *
         *
         *
         *
         */
  }

  @Test
  public void assertFleetIsReadyAfterPlacingIt() {

  }

  @Test
  public void assertFleetIsSunkAfterHittingAllShips() {

  }

}
