/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.extra;

import it.unibo.battleship.commons.*;
import it.unibo.battleship.ships.FleetFactory;
import it.unibo.battleship.ships.FleetFactoryImpl;
import it.unibo.battleship.shots.RandomLimitedShotFactory;
import it.unibo.battleship.shots.ShotFactory;
import it.unibo.battleship.shots.ShotImpl;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.time.Instant;
import java.util.Random;

/**
 * Represents an Artificial Intelligence which can create fleets or create
 * shots. See http://www.datagenetics.com/blog/december32011/ for advanced
 * algorithms
 *
 * @author fabio.urbini
 */
@Immutable
public abstract class AbstractArtificialIntelligence implements
    ArtificialIntelligence {
  // TODO: an Artificial Intelligence may need the field to generate things
  private static final long serialVersionUID = -7273836582211632939L;
  private final Boundary boundary;


  /**
   * Represents the Level of the Artificial Intelligence
   * @author fabio.urbini
   *
   */
  public enum Level {
    /** Guaranteed win */
    SUPER_EASY,

    /** Easy level */
    EASY,

    /** Average level, not supported yet */
    AVERAGE,

    /**
     * Hard level, you got to think more than a second for each move,
     * not supported yet
     */
    HARD,

    /**
     * Super hard level, you will need to build graphs and think like
     * an engineer to win.
     */
    SUPER_HARD
  }

  private AbstractArtificialIntelligence(final Boundary boundary) {
    this.boundary = boundary;
  }

  /**
   * Creates an ArtificialIntelligence depending on the {@link Level}.
   * @param level level of the AI
   * @param boundary current boundary used
   * @return an ArtificialIntelligence.
   */
  public static ArtificialIntelligence createArtificialIntelligence(
      final Level level, final Boundary boundary) {
    switch (level) {

    case SUPER_EASY:
      return new FreeWinAI(boundary);

    case EASY:
      return new EasyAI(boundary);

    case AVERAGE:
      throw new UnsupportedOperationException();

    case HARD:
      throw new UnsupportedOperationException();

    case SUPER_HARD:
      throw new UnsupportedOperationException();

    default:
      throw new IllegalArgumentException(GlobalProperties.INVALID_AI_LEVEL);
    }
  }

  public final Boundary getBoundary() {
    return BoundaryImpl.createBoundary(
        this.boundary.getColumnsCount(),
        this.boundary.getRowsCount()
    );
  }


  private static final class EasyAI extends AbstractArtificialIntelligence {
    private static final long serialVersionUID = -7273836582211632939L;
    final ShotFactory shotFactory;

    private EasyAI(final Boundary boundary) {
      super(boundary);
      this.shotFactory = new RandomLimitedShotFactory(boundary);
    }

    @Override
    public FleetFactory getFleetFactory() {
      return FleetFactoryImpl.INSTANCE;
    }

    @Override
    public ShotFactory getShotFactory() {
      return this.shotFactory;
    }
  }

  private static final class FreeWinAI extends AbstractArtificialIntelligence {
    private static final long serialVersionUID = -7970147935843938741L;

    private FreeWinAI(final Boundary boundary) {
      super(boundary);
    }

    @Override
    public FleetFactory getFleetFactory() {
      return FleetFactoryImpl.INSTANCE;
    }

    @Override
    public ShotFactory getShotFactory() {
      // Creates a new random shot
      return (ShotFactory) () -> ShotImpl
          .createShot(FreeWinAI.this.generateRandomPoint2d(this.getBoundary()));

    }

    private Point2d generateRandomPoint2d(final Boundary boundary) {
      final Random random = new Random(Instant.now().getNano());
      final int column = random.nextInt(boundary.getColumnsCount());
      final int row = random.nextInt(boundary.getRowsCount());

      return new Point2dImpl(column, row);
    }
  }

  private static final class SuperHardAi extends AbstractArtificialIntelligence {
    private static final long serialVersionUID = 3990811369592412792L;

    /*
     * http://www.datagenetics.com/blog/december32011/
     * Use New Algorithm here
     * This AI will need a Field reference
     */
    private SuperHardAi(final Boundary boundary) {
      super(boundary);
    }

    @Override
    public FleetFactory getFleetFactory() {
      // TODO: create another FleetFactory
      throw new UnsupportedOperationException();
    }

    @Override
    public ShotFactory getShotFactory() {
      throw new UnsupportedOperationException();
    }

  }
}
