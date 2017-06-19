package it.unibo.battleship.samples;

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
        StringBuilder sb = new StringBuilder("");

        sb.append("   ");

        String values = "0123456789ABCDEFGH";    // raw method to show header

        for (int i = 0; i < columnsCount; i++) {
            sb.append(' ').append(values.charAt(i)).append(' ');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Battleship game start");
        System.out.println("Type 1 or 2 for the following modes: ");
        System.out.println("-1 single player vs. the AI, hit its fleet");
        System.out.println("-2 single player vs. the AI, place your fleet");
        CONTROLLER.initialize();

        int columnsCount = CONTROLLER.getColumnsCount();

        stampaField(columnsCount);

        do {
            System.out.println("Create a new shot and point at the enemy fleet!");

            int row    = readInt("Enter row ");
            int column = readInt("Enter column ");

            CONTROLLER.shoot(row, column);
            stampaField(columnsCount);
        } while (CONTROLLER.checkToContinue());
    }

    private static int readInt(final String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(message);

        try {
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }

    private static void stampaField(final int columnsCount) {
        System.out.println(header(columnsCount));

        int i = 0;

        for (char[] chars : CONTROLLER.getCharMap()) {
            System.out.print(" " + i++ + ' ');

            for (char car : chars) {
                System.out.print(" " + car + ' ');
            }

            System.out.println();
        }

        System.out.println("\n\n\n");
    }
}

