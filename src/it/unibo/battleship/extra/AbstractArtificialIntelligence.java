package it.unibo.battleship.extra;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.common.Point2dImpl;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

import java.time.Instant;
import java.util.Random;

/**
 * Created by fabio.urbini on 05/06/2017.
 */
public abstract class AbstractArtificialIntelligence implements ArtificialIntelligence {

	private AbstractArtificialIntelligence() {

	}

	public final static ArtificialIntelligence createArtificialIntelligence(final Level level) {
		switch(level) {
			case FREE_WIN: return new FreeWinAI();
			case SUPER_EASY: throw new UnsupportedOperationException();
			case EASY: throw new UnsupportedOperationException();
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
		SUPER_HARD,
		;
	}

	private static final class FreeWinAI implements ArtificialIntelligence {

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

			final int x = random.nextInt(boundary.getColumnsCount());
			final int y = random.nextInt(boundary.getRowsCount());

			return new Point2dImpl(x, y);
		}
	}
}
