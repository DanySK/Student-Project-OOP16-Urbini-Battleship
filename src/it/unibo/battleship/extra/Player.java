package it.unibo.battleship.extra;

/**
 * Represents a Player.
 *
 * @author fabio.urbini
 */
public class Player {
  private final String name;

  /**
   * Not used yet.
   * @param name name of the player.
   */
  private Player(final String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
