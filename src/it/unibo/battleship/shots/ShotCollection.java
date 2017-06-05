package it.unibo.battleship.shots;

import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.common.Point2d;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by fabio.urbini on 05/06/2017.
 */
public abstract class ShotCollection {

	public abstract List<Shot> getShotCollection(final Point2d point2d);

	public static ShotCollection getShotCollectionByType(final ShotType shotType) {
		switch(shotType) {
			case X: return new XShot();
			case T: return new TShot();
			case I: return new IShot();
			default: throw new IllegalArgumentException(GlobalProperties.INVALID_SHOT_TYPE);
		}
	}
	public enum ShotType {
		X(5),
		T(5),
		I(3),
		;

		private final int totalSize;

		ShotType(final int totalSize) {
			this.totalSize = totalSize;
		}
	}

	public static final class XShot extends ShotCollection {

		@Override
		public List<Shot> getShotCollection(final Point2d point2d) {
			throw new NotImplementedException();
		}
	}

	public static final class TShot extends ShotCollection {

		@Override
		public List<Shot> getShotCollection(final Point2d point2d) {
			throw new NotImplementedException();
		}
	}

	public static final class IShot extends ShotCollection {

		@Override
		public List<Shot> getShotCollection(final Point2d point2d) {
			throw new NotImplementedException();
		}
	}
}
