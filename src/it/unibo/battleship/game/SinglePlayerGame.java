package it.unibo.battleship.game;

import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.commons.Ruleset;
import it.unibo.battleship.game.BattleshipController.PlayerType;
import it.unibo.battleship.game.BattleshipController.ViewerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static it.unibo.battleship.game.BattleshipController.CONTROLLER;
import static it.unibo.battleship.game.BattleshipController.PlayerType.AI;
import static it.unibo.battleship.game.BattleshipController.PlayerType.HUMAN;
import static it.unibo.battleship.game.BattleshipController.ViewerType.ENEMY;
import static it.unibo.battleship.game.BattleshipController.ViewerType.OWNER;

/**
 * @author fabio.urbini
 */
public final class SinglePlayerGame {
  private SinglePlayerGame() {
  }

  private static String getHeaderForConsole(final int columnsCount) {
    final StringBuilder sb = new StringBuilder("");

    sb.append("   ");

    final String values = "0123456789ABCDEFGH"; // raw method to show header

    for (int i = 0; i < columnsCount; i++) {
      sb.append(' ').append(values.charAt(i)).append(' ');
    }

    return sb.toString();
  }

  public static void main(final String[] args) {
    System.out.println("Battleship game start");
    System.out.println("Choose AI level");
    // TODO : read AI level, create a new AI, a new fleet and field
    System.out.println("Place your fleet first");
    CONTROLLER.initialize();

    printField(HUMAN, OWNER);
    do {
      place();
      printField(HUMAN, OWNER);
    } while (CONTROLLER.isPlayerFleetNotPlaced());

    do {
      System.out.println("Create a new shot and point at the enemy fleet!");

      final int row = readInt("Enter row ");
      final int column = readInt("Enter column ");

      CONTROLLER.shootAiField(row, column);
      CONTROLLER.shootPlayerField();
      printField(HUMAN, OWNER);
      printField(AI, ENEMY);
    } while (CONTROLLER.checkToContinue());


  }

  private static int readInt(final String message) {
    // TODO: use another class
    final BufferedReader br = new BufferedReader(new InputStreamReader(
        System.in));

    System.out.print(message);

    try {
      return Integer.parseInt(br.readLine());
    } catch (final NumberFormatException nfe) {
      System.err.println("Invalid Format!");
    } catch (final IOException e) {
      e.printStackTrace();
    }

    throw new IllegalArgumentException();
  }

  private static void printField(final PlayerType playerType, final ViewerType viewerType) {
    System.out.println("\n\n\n");

    System.out.println( playerType == AI ? "--- AI FIELD ---" : "--- PLAYER FIELD ---");
    System.out.println(getHeaderForConsole(CONTROLLER.getColumnsCount()));

    int i = 0;

    for (final char[] chars : CONTROLLER.getCharMap(playerType, viewerType)) {
      System.out.print(" " + i++ + ' ');

      for (final char car : chars) {
        System.out.print(" " + car + ' ');
      }

      System.out.println();
    }

    System.out.println("\n\n\n");
  }

  private static void place() {
    final String shipToPlace = CONTROLLER.getNextPlaceableShip();
    System.out.println(shipToPlace);

    final int row = readInt("Enter row to place a " + shipToPlace + ' ');
    final int column = readInt("Enter column to place a " + shipToPlace + ' ');
    // TODO: input = index, easier/faster to write.
    final Point2d p = new Point2dImpl(column, row);
    System.out.println(Ruleset.isPointWithinLimits(p));
    // Controllo che non tocchi altre navi
    // Stampa del campo qui di seguito
    CONTROLLER.placeNextPlaceableShip(p);
  }
}
