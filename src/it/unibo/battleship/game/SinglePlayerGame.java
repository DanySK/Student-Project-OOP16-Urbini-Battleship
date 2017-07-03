/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.game;

import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.game.BattleshipController.PlayerType;
import it.unibo.battleship.game.BattleshipController.ViewerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static it.unibo.battleship.game.BattleshipController.PlayerType.AI;
import static it.unibo.battleship.game.BattleshipController.PlayerType.HUMAN;
import static it.unibo.battleship.game.BattleshipController.ViewerType.ENEMY;
import static it.unibo.battleship.game.BattleshipController.ViewerType.OWNER;
import static it.unibo.battleship.game.BattleshipControllerImpl.CONTROLLER;

/**
 * Boundary of a battleship game. It works with the console.
 * @author fabio.urbini
 */
public final class SinglePlayerGame {
  /*
  Future refactorings :
  make an instantiable class controlling the console
  instantiate and use it in main
  Refactor ai/player methods so that they are generified
  Catch exceptions when creating a new ship or a new shot

  Make it easy to play with different boundaries
  place a ship or a shot with an index instead of row and column
   */
  private SinglePlayerGame() {
  }

  public static void main(final String[] args) {
    printStringsForGameStart();
    initializeAi();
    placePlayerFleet();

    do {
      shootAiField();
      shootPlayerField();
      printField(HUMAN, OWNER);
      printField(AI, ENEMY);
    } while (CONTROLLER.isGameNotFinished());

    printAfterGameEnded();
  }

  private static void printStringsForGameStart() {
    System.out.println("Battleship game start");
    System.out.println("CARE! Don't insert values out of the current boundary");
    System.out.println("Don't try to place ships that will be out of the boundaries");
    System.out.println("Don't make ships overlap, the game will end");
    System.out.println(getLegenda());
  }

  private static void initializeAi() {
    chooseAiLevel();
    CONTROLLER.initializeAi();
  }
  private static void printAfterGameEnded() {
    String s = "";
    if (CONTROLLER.isAiFleetSunk()) {
      s = "Congratulations! You won!";
    }
    if (CONTROLLER.isPlayerFleetSunk()) {
      s = "Sorry! You lost!";
    }
    System.out.println(s);
  }

  private static void shootAiField() {
    System.out.println("Create a new shot and point at the enemy fleet!");
    final Point2d p = getValidPoint2d();
    CONTROLLER.shootAiField(p);
  }

  private static void shootPlayerField() {
    CONTROLLER.shootPlayerField();
  }

  private static String getHeaderForConsole(final int columnsCount) {
    final StringBuilder sb = new StringBuilder("   ");
    final String values = "0123456789ABCDEFGH"; // raw method to show header

    for (int i = 0; i < columnsCount; i++) {
      sb.append(' ').append(values.charAt(i)).append(' ');
    }

    return sb.toString();
  }

  private static void placePlayerFleet() {
    System.out.println("Place your fleet first");
    printField(HUMAN, OWNER);
    do {
      place();
      printField(HUMAN, OWNER);
    } while (CONTROLLER.isPlayerFleetNotPlaced());
  }

  private static void chooseAiLevel() {
    boolean valid;
    do {
      printForAiLevel();
      final int aiLevel = readInt();
      valid = isValid(aiLevel, 1, 2);
      if (!valid) {
        System.out.println("Invalid number");
      } else {
        setUpAiLevel(aiLevel);
      }
    } while (!valid);
  }

  private static void printForAiLevel() {
    System.out.println("Choose AI level. Type one of the following values");
    System.out.println("1 : Super easy, guaranteed win, for beginners");
    System.out.println("2 : Easy, no need to think about moves yet");
  }

  private static void setUpAiLevel(final int aiLevel) {
    switch(aiLevel) {
      case 1 : {
        CONTROLLER.setUpAiLevelSuperEasy();
        System.out.println("Super easy AI!");
      }
      break;
      case 2 : {
        CONTROLLER.setUpAiLevelEasy();
        System.out.println("Easy AI!");
      }
      break;
      default : break;
    }
  }

  private static boolean isValid(final int input,
                                 final int minRangeInclusive,
                                 final int maxRangeInclusive) {
    return input <= maxRangeInclusive && input >= minRangeInclusive;
  }

  private static int writeMessageAndReadInt(final String message) {
    System.out.println(message);
    return readInt();
  }

  private static int readInt() throws NumberFormatException {
    final BufferedReader br = getBufferedReader();

    boolean check = false;
    do {
      try {
        check = true;
        return Integer.parseInt(br.readLine());
      } catch (final NumberFormatException nfe) {
        check = false;
        System.err.println("Invalid Format! It's not a number!");
        System.out.println("Reinsert value");
      } catch (final IOException e) {
        e.printStackTrace();
      }
    } while (!check);

    return -1;
  }

  private static BufferedReader getBufferedReader() {
    BufferedReader br = null;
    try {
      return new BufferedReader(new InputStreamReader(
          System.in, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return br;
  }

  private static void printField(final PlayerType playerType, final ViewerType viewerType) {
    final StringBuilder sb = new StringBuilder("\n\n");
    sb.append( playerType == AI ? "--- AI FIELD ---" : "--- PLAYER FIELD ---");
    sb.append('\n');
    sb.append(getHeaderForConsole(CONTROLLER.getBoundary().getColumnsCount()));
    System.out.println(sb);

    getFieldRowsToPrint(playerType, viewerType).forEach(System.out::println);

  }

  private static List<String> getFieldRowsToPrint(final PlayerType playerType, final ViewerType viewerType) {
    final List<String> rowsToWrite = new ArrayList<>();
    final List<List<Character>> charMapList = CONTROLLER.getFieldView(playerType, viewerType);

    IntStream.range(0, charMapList.size())
        .forEach( idx -> {
          final StringBuilder sb = new StringBuilder(" " + idx + ' ');
          charMapList.get(idx).forEach( (Character c) -> sb.append(" " + c + ' '));
          rowsToWrite.add(sb.toString());
        }
    );

    return rowsToWrite;
  }

  private static void place() {
    final String shipToPlace = CONTROLLER.getNextPlaceableShip();
    System.out.println("Trying to place a " + shipToPlace);
    final Point2d p = getValidPoint2d();
    CONTROLLER.placeNextPlaceableShip(p);
  }

  private static Point2d getValidPoint2d() {
    Point2d p;
    boolean pointOk;
    do {
     p = readPoint2d();
     pointOk = CONTROLLER.isPointWithinBoundaryLimits(p);
     if (!pointOk) {
       System.out.println("Invalid values for a point, reinsert them");
     }
    } while(!pointOk);
    return p;
  }

  private static Point2d readPoint2d() {
    final int row = writeMessageAndReadInt("Enter row to create a point");
    final int column = writeMessageAndReadInt("Enter column to create a point");
    return new Point2dImpl(column, row);
  }

  private static String getLegenda() {
    return "Legenda : \n" + "E = Empty\n" + "M = Missed\n" + "@ = Ship\n" + "* = Hit\n";
  }
}
