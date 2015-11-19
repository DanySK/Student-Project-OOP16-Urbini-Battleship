package it.unibo.battleship.controller;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.concreteclasses.ShipFactory;
import it.unibo.battleship.model.Fleet;
import it.unibo.battleship.model.FleetImpl;
import it.unibo.battleship.model.Ship;

public class Test {

    public static void main(String[] args) {

        Boundary boundary = new Boundary(10,10);
        Fleet f1 = new FleetImpl();
        Fleet f2 = new FleetImpl();
        ShipFactory factory = new ShipFactory(boundary);
        Ship tmp;

        // Modo molto rozzo per fare primi test
        tmp = factory.createShip(2, 2, 3);
        f1.addShip(tmp);
        f2.addShip(tmp);

        tmp = factory.createShip(2, 4, 5);
        f1.addShip(tmp);
        f2.addShip(tmp);

        tmp = factory.createShip(3, 30, 32);
        f1.addShip(tmp);
        f2.addShip(tmp);

        tmp = factory.createShip(3, 35, 37);
        f1.addShip(tmp);
        f2.addShip(tmp);

        tmp = factory.createShip(3, 42, 44);
        f1.addShip(tmp);
        f2.addShip(tmp);

        tmp = factory.createShip(4, 50, 53);
        f1.addShip(tmp);
        f2.addShip(tmp);

        tmp = factory.createShip(5, 55, 59);
        f1.addShip(tmp);
        f2.addShip(tmp);

        // Stampa delle due navi
        System.out.println("tipi navi flotta 1");
        for (Ship s : f1.getShips()) {
            Point2d sp = s.getStartingPosition();
            Point2d ep = s.getEndingPosition();
            System.out.print(s.getType() + " (" + sp.getX() + ";" + sp.getY() + ")");
            System.out.println(" to : (" + ep.getX() + ";" + ep.getY() + ")");
        }

        System.out.println("\n\ntipi navi flotta 2");
        for (Ship s : f2.getShips()) {
            Point2d sp = s.getStartingPosition();
            Point2d ep = s.getEndingPosition();
            System.out.print(s.getType() + " (" + sp.getX() + ";" + sp.getY() + ")");
            System.out.println(" to : (" + ep.getX() + ";" + ep.getY() + ")");
        }

        // Simulazione di spari senza controllo esterno
        for (int i = 2; i < 100; i++) {
            f1.tryHit(new Point2d(i, boundary));
            if (f1.isSunk()) {
                System.out.println("Affondata!!" + i);
                break;
            }
        }
        System.out.println("f1 affondata: " + f1.isSunk());
    }

}
