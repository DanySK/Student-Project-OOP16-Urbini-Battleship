package it.unibo.battleship.samples;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.commons.BoundaryImpl;
import it.unibo.battleship.commons.Point2dHelper;
import it.unibo.battleship.commons.Ruleset;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author fabio.urbini
 *
 */
public final class GameTest {

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {}

    @Test
    public void shotsTest() {
//        fail("Not yet implemented");
        final Boundary bounds = BoundaryImpl.createBoundary(10, 10);
        final int maxIndex = bounds.getColumnsNumber() * bounds.getRowsNumber();
        for (int i = 0; i < maxIndex; i++) {
        	final Shot s = ShotImpl.createShot(Point2dHelper
        			.getPoint2dByIndex(i, bounds));
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
    public void shipPlacementTest() {

    }

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {}
}

