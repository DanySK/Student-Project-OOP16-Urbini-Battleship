package it.unibo.battleship.controller;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.concreteclasses.ShipFactory;
import it.unibo.battleship.model.Fleet;
import it.unibo.battleship.model.FleetImpl;
import it.unibo.battleship.model.Ship;

public class Test {

    public static void main(String[] args) {

        Fleet f1 = new FleetImpl();
        Fleet f2 = new FleetImpl();
        ShipFactory factory = new ShipFactory(new Boundary(10,10));
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
        
        System.out.println("tipi navi flotta 1");
        for (Ship s : f1.getShips()) {
            System.out.println(s.getType());
        }
        
        System.out.println("tipi navi flotta 2");
        for (Ship s : f2.getShips()) {
            System.out.println(s.getType());
        }
    }

}
