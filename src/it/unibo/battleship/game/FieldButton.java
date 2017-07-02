package it.unibo.battleship.game;

import it.unibo.battleship.commons.Point2d;

import javax.swing.*;

/**
 * Wrapper of a JButton.
 *
 * @author fabio
 *
 */
public final class FieldButton {
  private final JButton btn;
  private final Point2d point;

  public FieldButton(final Point2d point) {
    this.point = point;
    this.btn = new JButton("" + point.getX() + ':' + point.getY());
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
   * Returns the {@link Point2d}.
   * @return the {@link Point2d}
   */
  public Point2d getPosition() {
    return this.point;
  }
}
