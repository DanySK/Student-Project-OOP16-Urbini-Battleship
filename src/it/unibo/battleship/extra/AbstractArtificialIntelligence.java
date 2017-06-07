package it.unibo.battleship.extra;

import it.unibo.battleship.common.*;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by fabio.urbini on 05/06/2017.
 */
public abstract class AbstractArtificialIntelligence implements ArtificialIntelligence {
	private final Boundary boundary;

	private AbstractArtificialIntelligence(final Boundary boundary) {
		this.boundary = boundary;
	}

	public final static ArtificialIntelligence createArtificialIntelligence(
			final Level level, final Boundary boundary) {

		switch(level) {
			case FREE_WIN: return new FreeWinAI(boundary);
			case SUPER_EASY: throw new UnsupportedOperationException();
			case EASY: return new EasyAI(boundary);
			case AVERAGE: throw new UnsupportedOperationException();
			case HARD: throw new UnsupportedOperationException();
			case SUPER_HARD: throw new UnsupportedOperationException();
			default : throw new IllegalArgumentException(GlobalProperties.INVALID_AI_LEVEL);
		}
	}

	public enum Level {
		FREE_WIN,
		SUPER_EASY,
		EASY,
		AVERAGE,
		HARD,
		SUPER_HARD;
	}

	public final Boundary getBoundary() {
		return BoundaryImpl.createBoundary(
				this.boundary.getColumnsCount(),
				this.boundary.getRowsCount()
		);
	}

	private static final class FreeWinAI extends AbstractArtificialIntelligence {

		private FreeWinAI(final Boundary boundary) {
			super(boundary);
		}

		@Override
		public Fleet createFleet() {
			// Creates a new random fleet without any order
			throw new UnsupportedOperationException();
		}

		@Override
		public Shot createShot(final Field field) {
			// Creates a new random shot without even looking at the field
			return ShotImpl.createShot(generateRandomPoint2d(field.getBoundary()));
		}

		private Point2d generateRandomPoint2d(final Boundary boundary) {
			Random random = new Random(Instant.now().getNano());

			final int column = random.nextInt(boundary.getColumnsCount());
			final int row = random.nextInt(boundary.getRowsCount());

			return new Point2dImpl(column, row);
		}
	}

	private static final class EasyAI extends AbstractArtificialIntelligence {
		final List<Integer> values;
		final int max;

		private EasyAI(final Boundary boundary) {
			super(boundary);
			max = boundary.getColumnsCount() * boundary.getRowsCount();
			this.values = new ArrayList<>(max);
			setUp();
		}

		@Override
		public Fleet createFleet() {
			return null;
		}

		@Override
		public Shot createShot(final Field field) {
			if (hasNextInt()) {
				return ShotImpl.createShot(Point2dHelper.getPoint2dByIndex(getRandomInt(), field.getBoundary()));
			}
			// There is no way the fleet couldn't be sunk by this time
			// Because all shots were generated.
			throw new IllegalStateException(GlobalProperties.INVALID_GENERATED_SHOTS_STATE);
		}

		private void setUp() {
			for (int i = 0; i < max; i++) {
				values.add(i);
			}
			Collections.shuffle(values);
		}

		private int getRandomInt() {
			if (!hasNextInt()) {
				throw new IllegalStateException(GlobalProperties.INVALID_GENERATED_SHOTS_STATE);
			}

			final int index = new Random()
					.ints(0, values.size())
					.limit(1)
					.iterator()
					.nextInt();
			final int val = values.get(index);
			values.remove(index);
			return val;
		}

		private boolean hasNextInt() {
			return !values.isEmpty();
		}
	}
}
