package tmpmodel;


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
                matrix[i][j] = new FieldCell();
            }
        }
    }

    public void updateStateWithShot(final Shot shot) {
        Point2d p = shot.getPoint();
        int x = p.getX();
        int y = p.getY();

        this.matrix[x][y].tryShoot(shot);
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

        // CONTROLLO SUL CAMPO -> non deve andar fuori
        // tutte le celle devono essere libere
        
        // CONTROLLO 1 : PUNTO DENTRO LA MAPPA
        if ( !Ruleset.isPointWithinLimits(point) ) {
            System.out.println("Punto scelto non dentro la mappa");
            return; // ECCEZIONE
        }
        
        //  CONTROLLO 2 : CELLA LIBERA
        FieldCell fieldCell = this.matrix[point.getX()][point.getY()];
        if ( !fieldCell.isEmpty() ) {
            System.out.println("Cella selezionata non libera");
            return; // CELLA GIA' OCCUPATA
        }
        
        // CONTROLLO 3 : NAVE DENTRO LA MAPPA
        if ( !Ruleset.isShipWithinLimits(ship, point)) {
            System.out.println("Nave fuori dalla mappa");
            return; // ECCEZIONE - nave fuori dalla mappa
        }
        
        // CONTROLLO 4 : CELLE SELEZIONATE LIBERE
        if ( !isShipPlaceable(ship, point)) {
            System.out.println("Celle selezionate non libere");
            return; // CELLE SELEZIONATE NON LIBERE - ECCEZIONE
        }
        ship.place(point);
        for (Point2d p : ship.getAllPositions()) {
            this.matrix[p.getY()][p.getX()].placeShip(ship);
        }

        // foreach con le celle coinvolte e linkare alla nave

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
