package it.unibo.battleship.common;

public class Point2d {

    private int x;
    private int y;
    private int index;
    private Boundary boundary;

    public Point2d(final int x, final int y) {
        // Control : x >= 0, y >= 0
        // Lazy pattern implementable
        this.x = x;
        this.y = y;
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
}
