package it.unibo.battleship.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.unibo.battleship.common.Point2dHelper;
import it.unibo.battleship.common.Point2dImpl;
import it.unibo.battleship.common.Ruleset;
import it.unibo.battleship.extra.AbstractArtificialIntelligence;
import it.unibo.battleship.extra.ArtificialIntelligence;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.map.FieldHelper;
import it.unibo.battleship.map.FieldImpl;
import it.unibo.battleship.ships.AbstractShip;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.ships.FleetImpl;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.shots.ShotImpl;

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

        ArtificialIntelligence ai =
            AbstractArtificialIntelligence.createArtificialIntelligence(AbstractArtificialIntelligence.Level.EASY,
                                                                        field1.getBoundary());

        for (Ship s : fleet.getAllShips()) {
            System.out.println(s.getAllPositions());
        }
        int i = 0;
        while (!fleet.isSunk() && (shots < 100)) {
//            Shot s = ai.createShot(field1);
            Shot s = ShotImpl.createShot(Point2dHelper.getPoint2dByIndex(i++, field1.getBoundary()));
            field1.updateStateWithShot(s);
            stampaField(field1);
            System.out.println(s);
            System.out.println(++shots);
        }

        // TODO: fleet wasn't sunk. WHY?
        System.out.println(fleet.isSunk());
        System.out.println("The end!");
        System.out.println(shots);
    }

    private static void placeFleet(final Field field, final Fleet fleet) {
        int  i = 0,
             j = 0;

        while (!fleet.isReady()) {
            if (fleet.getNextNonPlacedShip().isPresent()) {
            	final Ship ship = fleet.getNextNonPlacedShip().get();
                field.placeShip(ship, new Point2dImpl(i++, j++));
            }
        }

        stampaField(field);
    }

    private static void stampaField(final Field field) {
        for (char[] chars : FieldHelper.getViewByEnemy(field)) {
            for (char car : chars) {
                System.out.print(" " + car + ' ');
            }

            System.out.println();
        }

        System.out.println("\n\n\n");
    }
}

