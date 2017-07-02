package it.unibo.battleship.shots;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.commons.GlobalProperties;
import it.unibo.battleship.commons.Point2dHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Shot factory which produces a limited number
 * of random shots, depending on the current Boundary.
 * @author fabio.urbini
 */
public class RandomLimitedShotFactory implements ShotFactory {
  private static final long serialVersionUID = 5368676318205669298L;
  private final List<Integer> values;
  private final int max;

  public RandomLimitedShotFactory(final Boundary boundary) {
    this.max = boundary.getColumnsCount() * boundary.getRowsCount();
    this.values = new ArrayList<>(this.max);
    this.setUp();
  }

  @Override
  public Shot createShot() {
    this.checkNextInt();
    return ShotImpl.createShot(Point2dHelper.createPoint2d(this.getRandomInt()));
  }

  private void checkNextInt() {
    if (!this.hasNextInt()) {
      // There is no way the fleet couldn't be sunk at this time
      // Because all shots were generated.
      throw new IllegalStateException(
          GlobalProperties.INVALID_GENERATED_SHOTS_STATE
          + " Shots generated : " + (max - this.values.size())
      );
    }
  }

  private boolean hasNextInt() {
    return !this.values.isEmpty();
  }

  private int getRandomInt() {
    this.checkNextInt();

    final int index = new Random()
        .ints(0, this.values.size())
        .limit(1)
        .iterator()
        .nextInt();
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
