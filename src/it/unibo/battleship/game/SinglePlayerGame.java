package it.unibo.battleship.game;

import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Point2dImpl;
import it.unibo.battleship.commons.Ruleset;
import it.unibo.battleship.game.BattleshipController.PlayerType;
import it.unibo.battleship.game.BattleshipController.ViewerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
  private SinglePlayerGame() {
  }

  private static String getHeaderForConsole(final int columnsCount) {
    final StringBuilder sb = new StringBuilder("   ");

    final String values = "0123456789ABCDEFGH"; // raw method to show header

    for (int i = 0; i < columnsCount; i++) {
      sb.append(' ').append(values.charAt(i)).append(' ');
    }

    return sb.toString();
  }

  public static void main(final String[] args) {
    System.out.println("Battleship game start");
    System.out.println(getLegenda());
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
    } while (CONTROLLER.isGameNotFinished());


  }

  private static int readInt(final String message) {
    System.out.print(message);
    final BufferedReader br = new BufferedReader(new InputStreamReader(
        System.in));

    try {
      return Integer.parseInt(br.readLine());
    } catch (final NumberFormatException nfe) {
      System.err.println("Invalid Format! It's not a number!");
    } catch (final IOException e) {
      e.printStackTrace();
    }

    throw new IllegalArgumentException();
  }

  private static void printField(final PlayerType playerType, final ViewerType viewerType) {
    final StringBuilder sb = new StringBuilder("\n\n");
    sb.append( playerType == AI ? "--- AI FIELD ---" : "--- PLAYER FIELD ---");
    sb.append('\n');
    sb.append(getHeaderForConsole(CONTROLLER.getBoundary().getColumnsCount()));
    System.out.println(sb);

    getRowsToWrite(playerType, viewerType).forEach(System.out::println);

  }

  private static List<String> getRowsToWrite(final PlayerType playerType, final ViewerType viewerType) {
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

    final int row = readInt("Enter row to place the " + shipToPlace + ' ');
    final int column = readInt("Enter column to place the " + shipToPlace + ' ');
    // TODO: input = index, easier/faster to write.
    final Point2d p = new Point2dImpl(column, row);
    System.out.println(Ruleset.isPointWithinLimits(p));
    // Controllo che non tocchi altre navi
    // Stampa del campo qui di seguito
    CONTROLLER.placeNextPlaceableShip(p);
  }

  private static String getLegenda() {
    final StringBuilder legenda = new StringBuilder("Legenda : \n");
    legenda.append("E = Empty\n");
    legenda.append("M = Missed\n");
    legenda.append("@ = Ship\n");
    legenda.append("* = Hit\n");
    return legenda.toString();
  }
}
