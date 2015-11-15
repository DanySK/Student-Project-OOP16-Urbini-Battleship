package it.unibo.battleship.common;

/***
 * @author fabio
 * object used to define the Boundary of a field, grid, ...
 *
 */
public class Boundary {

    private final int xBound;
    private final int yBound;

    /***
     * @param horizontalBound Horizontal bound
     * @param verticalBound Vertical bound
     */
    public Boundary(final int horizontalBound, final int verticalBound) {
        this.xBound = horizontalBound;
        this.yBound = verticalBound;
    }

    /***
     * @return returns the Horizontal bound (x)
     */
    public final int getHorizontalBound() {
        return this.xBound;
    }

    /***
     * @return returns the Vertical bound (y)
     */
    public final int getVerticalBound() {
        return this.yBound;
    }
}
