package it.unibo.battleship.samples;

import it.unibo.battleship.common.Point2dImpl;
import it.unibo.battleship.common.Ruleset;
import it.unibo.battleship.extra.AbstractArtificialIntelligence;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.map.FieldHelper;
import it.unibo.battleship.map.FieldImpl;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.ships.FleetImpl;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Simple console game
 * @author fabio.urbini
 */
public final class ConsoleGame {

	public static void main(String[] args) {
		int shots = 0;
		// Creating a new field
		Field field1 = FieldImpl.createField(Ruleset.getBoundary());

		// Creating a new fleet
		Fleet fleet = FleetImpl.getNewFleet();

		// Placing the fleet in the field
		placeFleet(field1, fleet);

		do {
			shots++;
			Shot s = AbstractArtificialIntelligence
					.createArtificialIntelligence(
							AbstractArtificialIntelligence
							.Level
							.FREE_WIN
					).createShot(field1);
			field1.updateStateWithShot(s);
			stampaField(field1);
		} while (!fleet.isSunk());

		if (shots > 1000) {
			System.out.println("There is no way you can lose to the AI");
		}
		System.out.println("The end!");
		System.out.println(shots);
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
		for (char[] chars : FieldHelper.getViewByEnemy(field) ) {
			for (char car : chars ) {
				System.out.print(" " + car + ' ');
			}
			System.out.println();
		}
		System.out.println("\n\n\n");
	}

}
