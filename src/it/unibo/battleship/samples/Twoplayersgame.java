package it.unibo.battleship.samples;

import it.unibo.battleship.common.Point2dImpl;
import it.unibo.battleship.common.Ruleset;
import it.unibo.battleship.ships.FleetImpl;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.map.Field;
import it.unibo.battleship.map.FieldImpl;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.ShotImpl;

public class Twoplayersgame {

    public static void main(String[] args) {
        Field field1 = FieldImpl.createField(Ruleset.getBoundary());
        Field field2 = FieldImpl.createField(Ruleset.getBoundary());

        Fleet fleet1 = FleetImpl.getNewFleet();
        Fleet fleet2 = FleetImpl.getNewFleet();

        creaFlotta(field1, fleet1);
        creaFlotta(field2, fleet2);

        for (int i = 0; i < 10; i++ ) {
            for (int j = 0; j < 10; j++ )
                field1.updateStateWithShot(ShotImpl.createShot(new Point2dImpl(j,i)));
                if (fleet1.isSunk()) {
                    System.out.println("AFFONDATA");
                    break;
                }
        }
    }

    private static void creaFlotta(Field field1, Fleet fleet1) {
        int i = 0, j = 0;

        // PIAZZAMENTO FLOTTA
        Ship ship;
        while (!fleet1.isReady()) {

            if (fleet1.getNextNonPlacedShip().isPresent()) {
               ship = fleet1.getNextNonPlacedShip().get();
               field1.placeShip(ship, new Point2dImpl(i++, j++));

            }
        }

        stampaField(field1);
        System.out.println("\n\n\n");
    }

    private static void stampaField (final Field field) {
        for (char[] chars : field.getViewByOwner() ) {
            for (char car : chars ) {
                System.out.print(car);
            }
            System.out.println();
        }
    }
}
