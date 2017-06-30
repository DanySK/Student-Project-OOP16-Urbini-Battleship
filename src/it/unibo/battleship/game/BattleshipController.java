package it.unibo.battleship.game;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.commons.Point2d;

import java.util.List;

/**
 * Battleship controller for a Human vs AI game.
 * @author fabio.urbini
 */
public interface BattleshipController {

  /**
   * Returns {@code true} if the game is not finished yet.
   * @return {@code true} if the game is not finished yet.
   */
  boolean isGameNotFinished();

  /**
   * Tries to shoot the current cell selected.
   * @param row
   * a valid int between 0 and {@link Boundary#getRowsCount()} -1
   * @param column
   * a valid int between 0 and {@link Boundary#getColumnsCount()} -1
   * @throws IllegalArgumentException
   * if row or column don't form a valid Point2d
   */
  void shootAiField(int row, int column);

  /**
   * Tries to shoot the player field. It will generate a
   * suitable {@link Point2d}.
   */
  void shootPlayerField();

  /**
   * Get the proper field view depending on the player who owns it and
   * who is actually accessing it.
   * @param playerType the type of player who owns the field.
   * @param viewerType who is accessing the field.
   * @return the field view.
   */
  List<List<Character>> getFieldView(PlayerType playerType, ViewerType viewerType);

  /**
   * Returns the actual boundary used.
   * @return the actual boundary used.
   */
  Boundary getBoundary();

  /**
   * Returns the name of the next placeable ship.
   * @return the name of the next placeable ship.
   */
  String getNextPlaceableShip();

  /**
   * Tries to place the next placeable ship on the player
   * field.
   * @param startingPosition the position where the ship will start.
   * @throws IllegalArgumentException if the Point2d is invalid
   */
  void placeNextPlaceableShip(Point2d startingPosition);

  /**
   * Returns {@code true} if the player fleet isn't placed yet.
   * @return {@code true} if the player fleet isn't placed yet.
   */
  boolean isPlayerFleetNotPlaced();

  /**
   * Player type enumeration for Human and Ai game
   */
  enum PlayerType {
    /** Human type of player */
    HUMAN,

    /** Artificial intelligence / computer */
    AI
  }

  /**
   * Represents the types of viewers
   */
  enum ViewerType {
    /** The viewer of the field is the owner himself */
    OWNER,

    /** The viewer of the field is the enemy */
    ENEMY
  }
}
