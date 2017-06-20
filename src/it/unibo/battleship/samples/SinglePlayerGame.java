package it.unibo.battleship.samples;

import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dHelper;
import it.unibo.battleship.commons.Point2dImpl;

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
    System.out.println("Type 1 or 2 for the following modes: ");
    System.out.println("-1 single player vs. the AI, hit its fleet");
    System.out.println("-2 single player vs. the AI, place your fleet");
    CONTROLLER.initialize();

    final int columnsCount = CONTROLLER.getColumnsCount();
//    stampaField();

    System.out.println("Place your fleet");
    stampaField(false, true);
    do {
      place();
      stampaField(false, true);
    } while (CONTROLLER.playerFleetNotPlaced());

    do {
      System.out.println("Create a new shot and point at the enemy fleet!");

      final int row = readInt("Enter row ");
      final int column = readInt("Enter column ");

      CONTROLLER.shoot(row, column);
      stampaField(true, false);
    } while (CONTROLLER.checkToContinue());


  }

  private static int readInt(final String message) {
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

  private static void stampaField(final boolean isAi, final boolean isOwner) {
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

    Point2d p = new Point2dImpl(row, column);
    System.out.println(Point2dHelper.isPointWithinLimits(p));
    // Controllo che non tocchi altre navi
    CONTROLLER.placeNextPlaceableShip(p);
  }
}
