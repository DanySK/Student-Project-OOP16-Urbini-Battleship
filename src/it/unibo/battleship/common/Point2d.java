package it.unibo.battleship.common;

public class Point2d {

    private int x;
    private int y;
    private int index;
    private Boundary boundary;

    public Point2d(final int x, final int y, final Boundary boundary) {
        // Control : x >= 0, y >= 0
        // Lazy pattern implementable
        this.x = x;
        this.y = y;
        this.boundary = boundary;
        this.index = this.x + this.y * boundary.getHorizontalBound();
    }

    public Point2d(final int index, final Boundary boundary) {
        this.boundary = boundary;
        this.index = index;
        // Lazy pattern implementable
        this.x = index % boundary.getHorizontalBound();
        this.y = index / boundary.getHorizontalBound();
    }

    /***
	 *
	 * @return returns the x value of the 2 dimension point
	 */
	public final int getX() {
	    return this.x;
	}

	/***
	 *
	 * @return returns the y value of the 2 dimension point
	 */
	public final int getY() {
	    return this.y;
	}

	/***
	 *
	 * @return the index value of the 2 dimension point in its own boundary
	 */
	public final int getIndex() {
	    return this.index;
	}

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result
//                + ((boundary == null) ? 0 : boundary.hashCode());
//        result = prime * result + index;
//        result = prime * result + x;
//        result = prime * result + y;
//        return result;
//    }

	// RIFATTORIZZARE 
    public boolean equals(Object point) {
        if (point != null) {
            if (point.getClass() == this.getClass()) {
                if ((this.x == ((Point2d)point).getX()) && (this.y == ((Point2d)point).getY())) {
                    return true;
                }
            }
        }
        return false;
    }
	
}
