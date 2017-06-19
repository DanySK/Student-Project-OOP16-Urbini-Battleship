package it.unibo.battleship.ships;

import it.unibo.battleship.commons.GlobalProperties;
import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.commons.Ruleset.ShipRules;

/**
 * Implementation of a {@link ShipFactory}.
 * There can be only one Ship Factory at once.
 *
 * @author fabio.urbini
 */
public final class ShipFactoryImpl implements ShipFactory {
   private static final long serialVersionUID = -1375681121821315440L;
   /*
      * Ship factory singleton. There's no need to have
      * other instances around. The singleton is created
      * with the thread-safe lazy initialization.
      */
   private static ShipFactoryImpl instance;

   private ShipFactoryImpl() {
   }

   public static synchronized ShipFactoryImpl getInstance() {
      if (instance == null) {
         instance = new ShipFactoryImpl();
      }
      return instance;
   }

   /**
    * Creates a ship.
    *
    * @param size size of the ship. The value must be
    *             between 0 and {@see GlobalProperties.MAX_SIZE}
    * @return the ship created
    */
   @Override
   public Ship createShip(final int size) {
      if ((size < 0) || (size > GlobalProperties.MAX_SIZE)) {
         throw new IllegalArgumentException(GlobalProperties
              .INVALID_SHIP_SIZE);
      }

      switch (size) {
         case GlobalProperties.SUBMARINE_SIZE:
            return new Submarine();

         case GlobalProperties.CRUISER_SIZE:
            return new Cruiser();

         case GlobalProperties.BATTLESHIP_SIZE:
            return new Battleship();

         case GlobalProperties.AIR_CARRIER_SIZE:
            return new AirCarrier();

         default:
            throw new IllegalArgumentException(GlobalProperties
                 .INVALID_SHIP_SIZE);
      }
   }


   private static final class AirCarrier extends AbstractShip {
      private static final long serialVersionUID = -8323321815851042898L;

      AirCarrier() {
         super();
      }

      @SuppressWarnings("unused")
      AirCarrier(final Point2d start) {
         super(start);
      }

      @Override
      public String toString() {
         return ShipRules.AIR_CARRIER.toString();
      }

      @Override
      public int getSize() {
         return GlobalProperties.AIR_CARRIER_SIZE;
      }
   }


   private static final class Battleship extends AbstractShip {
      private static final long serialVersionUID = 8043537272411631772L;

      private Battleship() {
         super();
      }

      @SuppressWarnings("unused")
      private Battleship(final Point2d start) {
         super(start);
      }

      @Override
      public String toString() {
         return ShipRules.BATTLESHIP.toString();
      }

      @Override
      public int getSize() {
         //return GlobalProperties.ShipRules.BATTLESHIP.getSize();
         return GlobalProperties.BATTLESHIP_SIZE;
      }
   }


   private static final class Cruiser extends AbstractShip {
      private static final long serialVersionUID = -5532557604937632667L;

      private Cruiser() {
         super();
      }

      @SuppressWarnings("unused")
      private Cruiser(final Point2d start) {
         super(start);
      }

      @Override
      public String toString() {
         return ShipRules.CRUISER.toString();
      }

      @Override
      public int getSize() {
         //return GlobalProperties.ShipRules.CRUISER.getSize();
         return GlobalProperties.CRUISER_SIZE;
      }
   }


   private static final class Submarine extends AbstractShip {
      private static final long serialVersionUID = -2784639518931814680L;

      Submarine() {
         super();
      }

      @SuppressWarnings("unused")
      Submarine(final Point2d start) {
         super(start);
      }

      @Override
      public String toString() {
         return ShipRules.SUBMARINE.toString();
      }

      @Override
      public int getSize() {
         //return GlobalProperties.ShipRules.SUBMARINE.getSize();
         return GlobalProperties.SUBMARINE_SIZE;
      }
   }
}
