package it.unibo.battleship.samples;

import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.commons.Ruleset;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.map.FieldHelper;
import it.unibo.battleship.map.FieldImpl;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.ships.FleetFactoryImpl;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.ships.ShipDirection;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

/**
 * Singleton of a Controller of the battleship game.
 *
 * @author fabio.urbini
 */
public enum BattleshipController {
   CONTROLLER;

   private Fleet aiFleet;
   private Field aiField;

   public boolean checkToContinue() {
      return !this.aiFleet.isSunk();
   }

   public void initialize() {
      this.aiFleet = FleetFactoryImpl.getInstance().createFleet();
      this.aiField = FieldImpl.createField(Ruleset.BOUNDARY);
      placeFleet(this.aiField, this.aiFleet);
   }

   private static void placeFleet(final Field field, final Fleet fleet) {
      int i = 0,
           j = 0;

      while (!fleet.isReady()) {
         if (fleet.getNextNonPlacedShip().isPresent()) {
            final Ship ship = fleet.getNextNonPlacedShip().get();
            field.placeShip(ship, new Point2dImpl(i++, j++), ShipDirection.EAST);

            // Placing the ships diagonally
         }
      }
   }

   public void shoot(final int row, final int column) {
      final Shot shot = ShotImpl.createShot(new Point2dImpl(column, row));
      this.aiField.updateStateWithShot(shot);
   }

   public char[][] getCharMap() {
      return FieldHelper.getViewByEnemy(this.aiField);
   }

   public int getColumnsCount() {
      return this.aiField.getBoundary().getColumnsNumber();
   }
}

