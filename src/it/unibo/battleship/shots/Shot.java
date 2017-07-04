/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.shots;

import it.unibo.battleship.common.Point2d;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * Represents a single shot which can be thrown on the battlefield.
 *
 * @author fabio.urbini
 */
@Immutable
public interface Shot extends Serializable {
  /**
   * Gets the point of the shot.
   * @return the point of the shot.
   */
  Point2d getPoint();
}
