package it.unibo.battleship.ships;

public enum ShipsRuleset {
	SUBMARINE("Submarine", 2, 3),
	CRUISER("Cruiser", 3, 2),
	BATTLESHIP("Battleship", 4, 2),
	AIR_CARRIER("Air Carrier", 5, 1);

	private final String name;
	private final int size;
	private final int instancesNumber;

	private ShipsRuleset(final String name, final int size,
			final int instancesNumber) {
		this.name = name;
		this.size = size;
		this.instancesNumber = instancesNumber;
	}

	public final String getName() {
		return this.name;
	}
	public int getSize() {
		return this.size;
	}

	public int getInstancesNumber() {
		return this.instancesNumber;
	}
}
