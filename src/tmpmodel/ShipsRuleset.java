package tmpmodel;

public enum ShipsRuleset {
	SUBMARINE(2, 3),
	CRUISER(3, 2),
	BATTLESHIP(4, 2),
	AIR_CARRIER(5, 1);

	private final int size;
	private final int instancesNumber;
	
	private ShipsRuleset(final int size, final int instancesNumber) {
		this.size = size;
		this.instancesNumber = instancesNumber;
	}
	
	public int getSize() {
		return this.size;
	}

	public int getInstancesNumber() {
		return this.instancesNumber;
	}
}
