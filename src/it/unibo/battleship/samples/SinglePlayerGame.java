package it.unibo.battleship.samples;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.common.Point2dImpl;
import it.unibo.battleship.common.Ruleset;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.map.FieldHelper;
import it.unibo.battleship.map.FieldImpl;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.ships.FleetImpl;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by fabio.urbini on 06/06/2017.
 */
public class SinglePlayerGame {

	public static void main(String[] args) {
		Fleet AIFleet = FleetImpl.getNewFleet();
		Field AIField = FieldImpl.createField(Ruleset.getBoundary());

		placeFleet(AIField, AIFleet);

		do {
			System.out.println("Create a new shot and point at the enemy fleet!");
			int row = readInt("Enter row ");
			int column = readInt("Enter column ");
			Shot shot = ShotImpl.createShot(new Point2dImpl(column, row));
			AIField.updateStateWithShot(shot);
			stampaField(AIField);
		} while(!AIFleet.isSunk());
	}

	private static void placeFleet(final Field field,
	                               final Fleet fleet) {
		int i = 0, j = 0;

		Ship ship;
		while (!fleet.isReady()) {

			if (fleet.getNextNonPlacedShip().isPresent()) {
				ship = fleet.getNextNonPlacedShip().get();
				field.placeShip(ship, new Point2dImpl(i++, j++));

			}
		}

		stampaField(field);

	}

	private static void stampaField (final Field field) {
		System.out.println(header(field.getBoundary()));
		int i = 0;
		for (char[] chars : FieldHelper.getViewByEnemy(field) ) {
			System.out.print(" " + i++ + " ");
			for (char car : chars ) {
				System.out.print(" " + car + ' ');
			}
			System.out.println();
		}
		System.out.println("\n\n\n");
	}

	private static int readInt(final String message) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(message);
		try{
			return Integer.parseInt(br.readLine());
		}catch(NumberFormatException nfe){
			System.err.println("Invalid Format!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException();
	}

	private static String header(final Boundary boundary) {
		final int n = boundary.getColumnsCount();
		StringBuilder sb = new StringBuilder("");
		sb.append("   ");
		String values = "0123456789ABCDEFGH";

		for (int i = 0; i < n; i++) {

			sb.append(" " + values.charAt(i) + ' ');
		}
		return sb.toString();
	}
}
