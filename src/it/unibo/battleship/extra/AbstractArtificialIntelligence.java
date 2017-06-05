package it.unibo.battleship.extra;

import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.shots.Shot;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by fabio.urbini on 05/06/2017.
 */
public abstract class AbstractArtificialIntelligence implements ArtificialIntelligence {

	private AbstractArtificialIntelligence() {

	}

	public final static ArtificialIntelligence createArtificialIntelligence(final Level level) {
		switch(level) {

			case SUPER_EASY: return new SuperEasyAI();
			case EASY: throw new NotImplementedException();
			case AVERAGE: throw new NotImplementedException();
			case HARD: throw new NotImplementedException();
			case SUPER_HARD: throw new NotImplementedException();
			default : throw new IllegalArgumentException(GlobalProperties.INVALID_AI_LEVEL);
		}
	}

	public enum Level {
		SUPER_EASY,
		EASY,
		AVERAGE,
		HARD,
		SUPER_HARD,
		;
	}

	private static final class SuperEasyAI implements ArtificialIntelligence {

		@Override
		public Fleet createFleet() {
			// Creates a new random fleet without any order
			throw new NotImplementedException();
		}

		@Override
		public Shot createShot(final Field field) {
			// Creates a new random shot without even looking at the field
			throw new NotImplementedException();
		}
	}
}
