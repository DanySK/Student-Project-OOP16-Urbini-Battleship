package it.unibo.battleship.samples;

import javax.swing.*;

/**
 * Wrapper of a JButton.
 * 
 * @author fabio
 *
 */
public final class FieldButton {
  private final JButton btn;
  private final int x;
  private final int y;

  public FieldButton(final int x, final int y) {
    this.btn = new JButton("" + x + ':' + y);
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the JButton contained in the object.
   * 
   * @return the JButton
   */
  public JButton getButton() {
    return this.btn;
  }

  /**
   * Returns the x value.
   * 
   * @return the x value.
   */
  public int getX() {
    return this.x;
  }

  /**
   * The y value.
   * 
   * @return the y value.
   */
  public int getY() {
    return this.y;
  }
}
