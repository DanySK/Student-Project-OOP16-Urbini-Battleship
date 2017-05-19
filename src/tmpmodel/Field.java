package tmpmodel;

/**
 * TO DO :
 * ----- VISTA DEL NEMICO / VISTA DEL GIOCATORE ---------
 * @author fabio
 *
 */
/**
 * Represents the field
 * @author fabio
 *
 */
public class Field {
    private final FieldCell[][] matrix;
    private final int rows;
    private final int columns;

    public Field(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new FieldCell[rows][columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                matrix[i][j] = new FieldCellImpl();
            }
        }
    }

    public void updateStateWithShot(final Shot shot) {
        Point2d p = shot.getPoint();
        int x = p.getX();
        int y = p.getY();

        // I VALORI DEVONO ESSER ENTRO I LIMITI!!!!
        // LANCIARE ECCEZIONE?
        this.matrix[y][x].tryShoot(shot);
    }

//    public void placeFleet(final Fleet fleet) {
//        for (final AbstractShip s : fleet.getAllShips()) {
//            this.placeShip(s);
//        }
//    }

    public void placeShip(final AbstractShip ship, final Point2d point) {
        // Per il momento viene solo messo lo stato delle celle a presente
        // TO DO : controllo sulla presenza di navi

        // ship non è ancora stata piazzata.

        validateShipPlacement(ship, point);

        ship.place(point);
        for (Point2d p : ship.getAllPositions()) {
            this.matrix[p.getY()][p.getX()].placeShip(ship);
        }
    }

private void validateShipPlacement(final AbstractShip ship, final Point2d point) {
	// CONTROLLO SUL CAMPO -> non deve andar fuori
	// tutte le celle devono essere libere

	// CONTROLLO 1 : PUNTO DENTRO LA MAPPA
	if ( !Ruleset.isPointWithinLimits(point) ) {
	    throw new IllegalArgumentException(GlobalProperties.POINT_NOT_WITHIN_LIMITS_EX);
	}

	//  CONTROLLO 3 : CELLE LIBERE
	if ( !isShipPlaceable(ship, point)) {
	    throw new IllegalArgumentException(GlobalProperties.FIELD_CELLS_NOT_EMPTY);
	}

	// CONTROLLO 3 : NAVE DENTRO LA MAPPA
	if ( !Ruleset.isShipWithinLimits(ship, point)) {
	    throw new IllegalArgumentException(GlobalProperties.SHIP_NOT_WITHIN_LIMITS);
	}
}

    public char[][] getMatrix() {
        char[][] chars = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (matrix[i][j].isEmpty()) {
                    chars[i][j] = 'E';
                }
                if (matrix[i][j].isPresent()) {
                    chars[i][j] = '*';
                }
                if (matrix[i][j].isMissed()) {
                    chars[i][j] = 'M';
                }
            }
        }

        return chars;
    }

    public boolean isShipSunk(final AbstractShip s) {
        // Il controllo potrebbe esser fatto su Field
        return s.isSunk();
    }

    private boolean isShipPlaceable(final AbstractShip ship, final Point2d point) {
        // TUTTI I PUNTI SONO LIBERI?
        for (Point2d p : ship.getProjectionPoints(point)) {
            if ( !this.matrix[p.getX()][p.getY()].isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
