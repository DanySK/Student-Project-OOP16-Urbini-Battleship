package tmpmodel;


public class Field {
    private final FieldCell[][] matrix;
    private final int rows;
    private final int columns; 
    
    public Field(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new FieldCell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new FieldCell(State.WATER);
            }
        }
    }

    public void updateStateWithShot(final Shot shot) {
        Point2d p = shot.getPoint();
        int x = p.getX();
        int y = p.getY();
        
        
        if (matrix[x][y].getCurrentState() == State.HIT) {
           // throw new Exception("Nave già colpita");
        }
        
        
        // Se la nave è presente, viene colpita
        if (matrix[x][y].getCurrentState() == State.PRESENT ) {
            matrix[x][y].setState(State.HIT);
        } else {
            matrix[x][y].setState(State.MISSED);
        }
    }

    public void placeFleet(final Fleet fleet) {
        for (final AbstractShip s : fleet.getAllShips()) {
            this.placeShip(s);
        }
    }
    public void placeShip(final AbstractShip ship) {
        // Per il momento viene solo messo lo stato delle celle a presente
        // TO DO : controllo sulla presenza di navi 
        for ( final Point2d point : ship.getAllPositions() ) {
            matrix[point.getX()][point.getY()].setState(State.PRESENT);
        }
    }

    public boolean isShipSunk(final AbstractShip s) {
        // Il controllo potrebbe esser fatto su Field
        return s.isSunk();
    }
}
