package it.unibo.battleship.extra;

import it.unibo.battleship.commons.*;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.ships.FleetFactory;
import it.unibo.battleship.ships.FleetFactoryImpl;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents an Artificial Intelligence which can create fleets or create
 * shots. See http://www.datagenetics.com/blog/december32011/ for advanced
 * algorithms
 *
 * @author fabio.urbini
 */
public abstract class AbstractArtificialIntelligence implements
    ArtificialIntelligence {
  private static final long serialVersionUID = -7273836582211632939L;
  private final Boundary boundary;

  public enum Level {
    FREE_WIN, SUPER_EASY, EASY, AVERAGE, HARD, SUPER_HARD
  }

  private AbstractArtificialIntelligence(final Boundary boundary) {
    this.boundary = boundary;
  }

  public static final ArtificialIntelligence createArtificialIntelligence(
      final Level level, final Boundary boundary) {
    // Static factory method
    switch (level) {
    case FREE_WIN:
      return new FreeWinAI(boundary);

    case SUPER_EASY:
      throw new UnsupportedOperationException();

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
    return BoundaryImpl.createBoundary(this.boundary.getColumnnsCount(),
        this.boundary.getRowsCount());
  }

  private static final class EasyAI extends AbstractArtificialIntelligence {
    private static final long serialVersionUID = -7273836582211632939L;
    final List<Integer> values;
    final int max;

    private EasyAI(final Boundary boundary) {
      super(boundary);
      this.max = boundary.getColumnnsCount() * boundary.getRowsCount();
      this.values = new ArrayList<>(this.max);
      this.setUp();
    }

    @Override
    public FleetFactory getFleetFactory() {
      return FleetFactoryImpl.getInstance();
    }

    @Override
    public Shot createShot(final Field field) {
      if (this.hasNextInt()) {
        return ShotImpl.createShot(Point2dHelper.createPoint2d(
            this.getRandomInt(), field.getBoundary()));
      }

      // There is no way the fleet couldn't be sunk at this time
      // Because all shots were generated.
      throw new IllegalStateException(
          GlobalProperties.INVALID_GENERATED_SHOTS_STATE);
    }

    private boolean hasNextInt() {
      return !this.values.isEmpty();
    }

    private int getRandomInt() {
      if (!this.hasNextInt()) {
        throw new IllegalStateException(
            GlobalProperties.INVALID_GENERATED_SHOTS_STATE);
      }

      final int index = new Random().ints(0, this.values.size()).limit(1)
          .iterator().nextInt();
      final int val = this.values.get(index);

      this.values.remove(index);

      return val;
    }

    private void setUp() {
      for (int i = 0; i < this.max; i++) {
        this.values.add(i);
      }

      Collections.shuffle(this.values);
    }
  }

  private static final class FreeWinAI extends AbstractArtificialIntelligence {
    private static final long serialVersionUID = -7970147935843938741L;

    private FreeWinAI(final Boundary boundary) {
      super(boundary);
    }

    @Override
    public FleetFactory getFleetFactory() {
      return FleetFactoryImpl.getInstance();
    }

    @Override
    public Shot createShot(final Field field) {
      // Creates a new random shot without even looking at the field
      return ShotImpl
          .createShot(this.generateRandomPoint2d(field.getBoundary()));
    }

    private Point2d generateRandomPoint2d(final Boundary boundary) {
      final Random random = new Random(Instant.now().getNano());
      final int column = random.nextInt(boundary.getColumnnsCount());
      final int row = random.nextInt(boundary.getRowsCount());

      return new Point2dImpl(column, row);
    }
  }

  private static final class SuperHardAi extends AbstractArtificialIntelligence {
    private static final long serialVersionUID = 3990811369592412792L;

    /*
     * http://www.datagenetics.com/blog/december32011/
     * Use New Algorithm here
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
    public Shot createShot(final Field field) {
      throw new UnsupportedOperationException();
    }

  }
}
