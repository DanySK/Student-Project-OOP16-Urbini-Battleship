package it.unibo.battleship.model.common;

/***
 * @author fabio
 * object used to define the Boundary of a field, grid, ...
 *
 */
public class Boundary {

    private final int horizontalBound;
    private final int verticalBound;

    /***
     * @param horizontalBound Horizontal bound
     * @param verticalBound Vertical bound
     */
    public Boundary(final int horizontalBound, final int verticalBound) {
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
    }

    /***
     * 
     * @return returns the Horizontal bound (x)
     */
    public final int getHorizontalBound() {
        return this.horizontalBound;
    }

    /***
     * @return returns the Vertical bound (y)
     */
    public final int getVerticalBound() {
        return this.verticalBound;
    }
}
