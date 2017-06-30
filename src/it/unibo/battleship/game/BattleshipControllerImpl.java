package it.unibo.battleship.game;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.commons.Ruleset;
import it.unibo.battleship.extra.AbstractArtificialIntelligence;
import it.unibo.battleship.extra.ArtificialIntelligence;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.map.FieldHelper;
import it.unibo.battleship.map.FieldImpl;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.ships.FleetFactoryImpl;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

import java.util.List;
import java.util.Optional;

/**
 * Singleton of a Controller of the battleship game.
 *
 * @author fabio.urbini
 */
public enum BattleshipControllerImpl implements BattleshipController {
  CONTROLLER;

  // TODO : Divide into two controller instances with fleet+field
  private final Fleet aiFleet;
  private final Field aiField;
  private final Fleet playerFleet;
  private final Field playerField;
  private final ArtificialIntelligence ai;

  BattleshipControllerImpl() {
    this.ai = AbstractArtificialIntelligence.createArtificialIntelligence(
        AbstractArtificialIntelligence.Level.EASY,
        Ruleset.BOUNDARY
    );
    this.aiFleet = this.ai.getFleetFactory().createFleet();
    this.aiField = FieldImpl.createField(Ruleset.BOUNDARY);
    this.playerFleet = FleetFactoryImpl.INSTANCE.createFleet();
    this.playerField = FieldImpl.createField(Ruleset.BOUNDARY);
  }

  @Override
  public boolean isGameNotFinished() {
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

  @Override
  public void shootAiField(final int row, final int column) {
    final Shot shot = ShotImpl.createShot(new Point2dImpl(column, row));
    this.aiField.updateStateWithShot(shot);
  }

  @Override
  public void shootPlayerField() {
    this.playerField.updateStateWithShot(this.ai.getShotFactory().createShot());
  }

  @Override
  public List<List<Character>> getFieldView(final PlayerType playerType, final ViewerType viewerType) {
    return this.getViewList(viewerType, this.getField(playerType));
  }

  private List<List<Character>> getViewList(final ViewerType viewerType, final Field field) {

    switch(viewerType) {
      case OWNER:
        return FieldHelper.getViewByOwner(field);
      case ENEMY:
        return FieldHelper.getViewByEnemy(field);
      default :
        throw new IllegalStateException("Invalid state for ViewerType");
    }
  }

  private Field getField(final PlayerType playerType) {
    switch(playerType) {
      case HUMAN:
        return this.playerField;
      case AI:
        return this.aiField;
      default :
        throw new IllegalStateException("Invalid state for PlayerType");
    }
  }

  @Override
  public Boundary getBoundary() {
    return Ruleset.BOUNDARY;
  }

  @Override
  public String getNextPlaceableShip() {
    final Optional<Ship> ship = this.playerFleet.getNextNonPlacedShip();
    return ship.map(Object::toString).orElse("No ship left to place");
  }

  @Override
  public void placeNextPlaceableShip(final Point2d startingPosition) {
    // TODO: controllo della posizione con RULESET
    final Optional<Ship> ship = this.playerFleet.getNextNonPlacedShip();
    ship.ifPresent(ship1 -> this.playerField.placeShip(ship1, startingPosition));
  }

  @Override
  public boolean isPlayerFleetNotPlaced() {
    return !this.playerFleet.isReady();
  }



}
