package it.unibo.battleship.common;

public final class GlobalProperties {
	public static final int MAX_SIZE = 5;
	public static final int SUBMARINE_SIZE = 1;
	public static final int CRUISER_SIZE = 2;
	public static final int BATTLESHIP_SIZE = 3;
	public static final int AIR_CARRIER_SIZE = 4;
	public static final String POINT_NOT_WITHIN_LIMITS_EX =
			"Punto non definito entro i limiti della mappa";
	public static final String FIELD_CELLS_NOT_EMPTY =
			"Celle della mappa già utilizzate";
	public static final String SHIP_NOT_WITHIN_LIMITS =
			"Si sta cercando di posizionare una nave fuori dalla mappa";
	public static final String BOUNDARY_VALUE_IS_NEGATIVE =
			"Boundary values should be positive (greater than 0)";
	private GlobalProperties() {} 
	
	public enum ShipRules {
		SUBMARINE("Submarine", SUBMARINE_SIZE),
		CRUISER("Cruiser", CRUISER_SIZE),
		BATTLESHIP("Battleship", BATTLESHIP_SIZE),
		AIR_CARRIER("Air Carrier", AIR_CARRIER_SIZE),
		;
		
		private final String name;
		private final int size;
		
		ShipRules(final String name, final int size) {
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
