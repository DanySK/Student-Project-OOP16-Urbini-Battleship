package it.unibo.battleship.samples;

import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dHelper;
import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.commons.Ruleset;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.map.FieldHelper;
import it.unibo.battleship.map.FieldImpl;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.ships.FleetFactoryImpl;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;
import java.util.Optional;

/**
 * Singleton of a Controller of the battleship game.
 *
 * @author fabio.urbini
 */
public enum BattleshipController {
  CONTROLLER;

  // TODO : Divide into two controller instances with fleet+field
  private final Fleet aiFleet;
  private final Field aiField;
  private final Fleet playerFleet;
  private final Field playerField;

  BattleshipController() {
    this.aiFleet = FleetFactoryImpl.getInstance().createFleet();
    this.aiField = FieldImpl.createField(Ruleset.BOUNDARY);
    this.playerFleet = FleetFactoryImpl.getInstance().createFleet();
    this.playerField = FieldImpl.createField(Ruleset.BOUNDARY);
  }

  public boolean checkToContinue() {
    return !this.aiFleet.isSunk();
  }

  public void initialize() {
    placeFleet(this.aiField, this.aiFleet);
  }

  private static void placeFleet(final Field field, final Fleet fleet) {
    int i = 0, j = 0;

    while (!fleet.isReady()) {
      if (fleet.getNextNonPlacedShip().isPresent()) {
        final Ship ship = fleet.getNextNonPlacedShip().get();
        field.placeShip(ship, new Point2dImpl(i++, j++));

        // Placing the ships diagonally
      }
    }
  }

  public void shoot(final int row, final int column) {
    final Shot shot = ShotImpl.createShot(new Point2dImpl(column, row));
    this.aiField.updateStateWithShot(shot);
  }

  public char[][] getCharMap(boolean isAi, boolean isOwner) {
    Field field = isAi ? this.aiField : this.playerField;
    if (isOwner) {
      return FieldHelper.getViewByOwner(field);
    }
    return FieldHelper.getViewByEnemy(field);
  }

  public int getColumnsCount() {
    return Ruleset.BOUNDARY.getColumnnsCount();
  }

  public String getNextPlaceableShip() {
    Optional<Ship> ship = playerFleet.getNextNonPlacedShip();
    if (ship.isPresent()) {
      return ship.get().toString();
    }
    return "No ship left to place";
  }

  public void placeNextPlaceableShip(final Point2d startingPosition) {
    // TODO: controllo della posizione con RULESET
    Optional<Ship> ship = playerFleet.getNextNonPlacedShip();
    if (ship.isPresent()) {
      playerField.placeShip(ship.get(), startingPosition);
    }

    for (int i = 0; i < 99; i++) {
      char c = '0';
      String fin = i > 9 ? i + "" : c + "" + i;
      System.out.print(fin + '-');
    }
    System.out.println();
    for (int i = 0; i < 99; i++) {
      Point2d p = Point2dHelper.createPoint2d(i, Ruleset.BOUNDARY);
      boolean pres = playerField.getFieldCells()[p.getY()][p.getX()].isPresent();
      String tmp = "-" + (pres ? '@' : 'x');
      System.out.print(tmp + '-');
      // TODO: check the ship is not placed or viewed correctly
      // cruiser placed at 7,4 -> (7;4), (8;4) instead of (7;4), (7;5)
    }
  }

  public boolean playerFleetNotPlaced() {
    return !playerFleet.isReady();
  }


}
