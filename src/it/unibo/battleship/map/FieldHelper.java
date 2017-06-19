package it.unibo.battleship.map;

/**
 * Helper class for the Field.
 *
 * @author fabio.urbini
 */
// TODO: move out magic numbers and magic chars?
public final class FieldHelper {
   private enum PlayerState {OWNER, ENEMY}

   private FieldHelper() {
   }

   private static char getValueByPlayerState(final PlayerState playerState, final FieldCell cell) {
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
         switch (playerState) {
            case OWNER:
               return '@';

            case ENEMY:
               return 'E';

            default:
               throw new IllegalStateException();
         }
      }

      throw new IllegalStateException();    // TODO: Exception?
   }

   /**
    * Returns a representation of the field
    * viewed by the enemy.
    * E stands for Empty
    * M stands for Missed
    *
    * @return a representation of the field
    * seen by the enemy
    * @ stands for ship present
    * * stands for ship hit
    */
   public static char[][] getViewByEnemy(final Field field) {
      return getViewByPlayerState(field, PlayerState.ENEMY);
   }

   /**
    * Returns a representation of the field
    * viewied by the owner of the field
    * E stands for Empty
    * M stands for Missed
    *
    * @return a representation of the field
    * viewed by the owner of the field
    * @ stands for ship present
    * * stands for ship hit
    */
   public static char[][] getViewByOwner(final Field field) {
      return getViewByPlayerState(field, PlayerState.OWNER);
   }

   private static char[][] getViewByPlayerState(final Field field, final PlayerState playerState) {
      final int rows = field.getBoundary().getRowsNumber();
      final int columns = field.getBoundary().getColumnsNumber();
      final char[][] view = new char[rows][columns];

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < columns; j++) {
            view[i][j] = getValueByPlayerState(playerState, field.getFieldCells()[j][i]);
         }
      }

      return view;
   }
}

