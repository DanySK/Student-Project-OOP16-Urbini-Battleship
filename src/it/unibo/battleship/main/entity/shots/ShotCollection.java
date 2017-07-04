/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.shots;

import it.unibo.battleship.main.common.GlobalProperties;
import it.unibo.battleship.main.common.Point2d;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a collection of shots. It can be extended by subclasses by
 * defining getShotCollection.
 *
 * @author fabio.urbini
 */
public abstract class ShotCollection implements Serializable {
  private static final long serialVersionUID = -8675395549867915077L;

  private ShotCollection() {
  }

  /**
   * Type of the Shot collection.
   * @author fabio.urbini
   *
   */
  public enum Type {
    X(5),
    T(5),
    I(3);

    private final int totalSize;

    Type(final int totalSize) {
      this.totalSize = totalSize;
    }

    public int getTotalSize() {
      return this.totalSize;
    }
  }

  public abstract Iterable<Shot> getShotCollection(final Point2d point2d);

  /**
   * Returns a ShotCollection given the input shot type.
   * @param shotType shot type of the shot collection
   * @return a shot collection.
   */
  public static ShotCollection getShotCollectionByType(final Type shotType) {
    switch (shotType) {
    case X:
      return new XShot();

    case T:
      return new TShot();

    case I:
      return new IShot();

    default:
      throw new IllegalArgumentException(GlobalProperties.INVALID_SHOT_TYPE);
    }
  }


  private static final class IShot extends ShotCollection {
    private static final long serialVersionUID = 6019046647320383763L;

    @Override
    public List<Shot> getShotCollection(final Point2d point2d) {
      throw new UnsupportedOperationException();
    }
  }


  private static final class TShot extends ShotCollection {
    private static final long serialVersionUID = -1817573103704728259L;

    @Override
    public List<Shot> getShotCollection(final Point2d point2d) {
      throw new UnsupportedOperationException();
    }
  }


  private static final class XShot extends ShotCollection {
    private static final long serialVersionUID = 8779342634741299600L;

    @Override
    public List<Shot> getShotCollection(final Point2d point2d) {
      throw new UnsupportedOperationException();
    }
  }
}
