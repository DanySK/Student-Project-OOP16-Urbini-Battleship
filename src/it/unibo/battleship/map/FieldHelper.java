package it.unibo.battleship.map;

/**
 * Created by fabio.urbini on 06/06/2017.
 */
public final class FieldHelper {
	private FieldHelper() {}

	/**
	 * Returns a representation of the field
	 * viewied by the owner of the field
	 * @return
	 */
	public static char[][] getViewByOwner(Field field) {
		return getViewByPlayerState(field, PlayerState.OWNER);
	}

	/**
	 * Returns a representation of the field
	 * viewied by the enemy
	 * @return a representation of the field
	 * seen by the enemy
	 */
	public static char[][] getViewByEnemy(final Field field) {
		return getViewByPlayerState(field, PlayerState.ENEMY);
	}

	private static char[][] getViewByPlayerState(final Field field,
	                                             final PlayerState playerState) {
		final int rows = field.getBoundary().getVerticalBound();
		final int columns = field.getBoundary().getHorizontalBound();
		char[][] view = new char[rows][columns];
		for (int i = 0; i < rows; i++ ) {
			for (int j = 0; j < columns; j++ ) {
				view[i][j] = getValueByPlayerState(
						playerState,
						field.getFieldCells()[j][i]
				);
			}
		}
		return view;
	}

	private static char getValueByPlayerState(final PlayerState playerState,
	                                   final FieldCell cell) {
		if (cell.isEmpty()) {
			return 'E';
		}
		if (cell.isMissed()) {
			return 'M';
		}

		if (cell.isHit()) {
			return '*';
		}

		if (cell.isPresent()) {
			switch(playerState) {
				case OWNER: return '@';
				case ENEMY: return 'E';
			}
		}
		throw new IllegalStateException(); // TODO: Exception?
	}

	private enum PlayerState {
		OWNER,
		ENEMY
	}
}
