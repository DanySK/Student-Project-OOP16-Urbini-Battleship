package it.unibo.battleship.samples;

import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.commons.Ruleset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static it.unibo.battleship.samples.BattleshipController.CONTROLLER;

/**
 * @author fabio.urbini
 */
public final class SinglePlayerGame {
  private SinglePlayerGame() {
  }

  private static String header(final int columnsCount) {
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

    final int columnsCount = CONTROLLER.getColumnsCount();
    printField(false, true);
    do {
      place();
      printField(false, true);
    } while (CONTROLLER.isPlayerFleetNotPlaced());

    do {
      System.out.println("Create a new shot and point at the enemy fleet!");

      final int row = readInt("Enter row ");
      final int column = readInt("Enter column ");

      CONTROLLER.shoot(row, column);
      printField(true, false);
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

  private static void printField(final boolean isAi, final boolean isOwner) {
    System.out.println(header(CONTROLLER.getColumnsCount()));

    int i = 0;

    for (final char[] chars : CONTROLLER.getCharMap(isAi, isOwner)) {
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

    final Point2d p = new Point2dImpl(column, row);
    System.out.println(Ruleset.isPointWithinLimits(p));
    // Controllo che non tocchi altre navi
    // Stampa del campo qui di seguito
    CONTROLLER.placeNextPlaceableShip(p);
  }
}
