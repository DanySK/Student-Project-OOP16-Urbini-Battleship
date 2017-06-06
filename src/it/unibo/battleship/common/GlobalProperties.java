package it.unibo.battleship.common;

/**
 * Contains the global properties of the battleship game,
 * including each ship size.
 * @author fabio.urbini
 */
public final class GlobalProperties {
	public static final int MAX_SIZE = 5;
	public static final int SUBMARINE_SIZE = 1;
	public static final int CRUISER_SIZE = 2;
	public static final int BATTLESHIP_SIZE = 3;
	public static final int AIR_CARRIER_SIZE = 4;
	public static final String POINT_NOT_WITHIN_LIMITS =
			"Punto non definito entro i limiti della mappa";
	public static final String FIELD_CELLS_NOT_EMPTY =
			"Celle della mappa già utilizzate";
	public static final String SHIP_NOT_WITHIN_LIMITS =
			"Ship points are not within the boundary of the field";
	public static final String BOUNDARY_VALUE_IS_NEGATIVE =
			"Boundary values should be positive (greater than 0)";
	public static final String INVALID_SHOT_TYPE =
			"Invalid shot type";
	public static final String INVALID_AI_LEVEL =
			"Invalid artificial intelligence level";
	public static final String INVALID_SHIP_SIZE =
			"Invalid ship size";

	private GlobalProperties() {} 
	
	public enum ShipRules {
		SUBMARINE("Submarine", SUBMARINE_SIZE),
		CRUISER("Cruiser", CRUISER_SIZE),
		BATTLESHIP("Battleship", BATTLESHIP_SIZE),
		AIR_CARRIER("Air Carrier", AIR_CARRIER_SIZE),
		// new ships here
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
