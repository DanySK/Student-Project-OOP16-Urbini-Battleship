package tmpmodel;

public class Shot {
    /* lo sparo pu� esser formato da pi� punti
       oppure uno sparo di un certo tipo � formato da pi� spari
       ci� potrebbe portar al problema nel caso una casella sia gi� stata colpita
       es : WWHHHWW   (water, hit)
       se voglio colpire la prima H con uno sparo da 1, errore
       se voglio colpire WWH con uno sparo da 3, non deve esserci eccezione
   
   */
    private final Point2d point;

    private Shot (final Point2d p) {
        this.point = p;
    }
    
    public Point2d getPoint() {
        return this.point;
    }
    // FACTORY METHODS
    
    public static Shot createShot(final Point2d p) {
        if (Ruleset.isPointWithinLimits(p)) {
            return new Shot(p);
        } else throw new IllegalArgumentException("Punto dato non entro i limiti");
    }
}
