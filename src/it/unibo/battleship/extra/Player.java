package it.unibo.battleship.extra;

/**
 * Represents a Player.
 * Not supported yet.
 * @author fabio.urbini
 */
public class Player {
  private final String name;

  /**
   * Not usable yet.
   * @param name name of the player.
   */
  private Player(final String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
