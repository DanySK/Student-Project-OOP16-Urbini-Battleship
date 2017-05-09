package tmpmodel;

public final class GlobalProperties {
	public static final int MAX_SIZE = 5;
	public static final int SUBMARINE_SIZE = 1;
	public static final int CRUISER_SIZE = 2;
	public static final int BATTLESHIP_SIZE = 3;
	public static final int AIR_CARRIER_SIZE = 4;
	
	public static enum EnumNave {
		SUBMARINE("Submarine", SUBMARINE_SIZE),
		CRUISER("Cruiser", CRUISER_SIZE),
		BATTLESHIP("Battleship", BATTLESHIP_SIZE),
		AIR_CARRIER("Air Carrier", AIR_CARRIER_SIZE),
		;
		
		private final String name;
		private final int size;
		
		EnumNave(final String name, final int size) {
			this.name = name;
			this.size = size;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
		
		public int getSize() {
			return this.size;
		}
	}
}
