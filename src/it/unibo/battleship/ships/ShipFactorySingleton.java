package it.unibo.battleship.ships;

/**
 * Singleton of a {@link ShipFactory}.
 * @author fabio.urbini
 *
 */
public enum ShipFactorySingleton {
	/**
	 * Singleton of a ShipFactory.
	 */
	SINGLETON;

	private final ShipFactory shipFactory;

	private ShipFactorySingleton() {
		this.shipFactory = new ShipFactoryImpl();
	}

	/**
	 * Returns the factory of ships.
	 * @return the {@link ShipFactory}
	 */
	public ShipFactory getFactory() {
		// possible edit : return a new copy every time
		// and remove constructor plus local variable
		return this.shipFactory;
	}
}
