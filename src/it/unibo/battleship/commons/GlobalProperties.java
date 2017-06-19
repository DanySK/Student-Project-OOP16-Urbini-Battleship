package it.unibo.battleship.commons;

/**
 * Contains the global properties of the battleship game,
 * including each ship size.
 * @author fabio.urbini
 */
public final class GlobalProperties {
    public static final int    MAX_SIZE                      = 5;
    public static final int    SUBMARINE_SIZE                = 1;
    public static final int    CRUISER_SIZE                  = 2;
    public static final int    BATTLESHIP_SIZE               = 3;
    public static final int    AIR_CARRIER_SIZE              = 4;
    public static final String POINT_NOT_WITHIN_LIMITS       = "Point not defined within the map limit";
    public static final String FIELD_CELLS_NOT_EMPTY         = "Field cell are not empty";
    public static final String SHIP_NOT_WITHIN_LIMITS        = "Ship points are not within the boundary of the field";
    public static final String BOUNDARY_VALUE_IS_NEGATIVE    = "Boundary values should be positive (greater than 0)";
    public static final String INVALID_SHOT_TYPE             = "Invalid shot type";
    public static final String INVALID_AI_LEVEL              = "Invalid artificial intelligence level";
    public static final String INVALID_SHIP_SIZE             = "Invalid ship size";
    public static final String INVALID_GENERATED_SHOTS_STATE = "Shots generated were more than the actual map size";
    public static final String STARTING_POSITION_NOT_DEFINED = "Starting position wasn't defined yet";
    private GlobalProperties() {}

    public enum ShipRules {
        SUBMARINE("Submarine", SUBMARINE_SIZE, 3),
        CRUISER("Cruiser", CRUISER_SIZE, 2),
        BATTLESHIP("Battleship", BATTLESHIP_SIZE, 2),
        AIR_CARRIER("Air Carrier", AIR_CARRIER_SIZE, 0);

        private final String name;
        private final int    size;
        private final int    instancesNumber;

        ShipRules(final String name,
        		final int size,
        		final int instancesNumber) {
            this.name            = name;
            this.size            = size;
            this.instancesNumber = instancesNumber;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public int getInstancesNumber() {
            return this.instancesNumber;
        }

        public int getSize() {
            return this.size;
        }
    }
}

