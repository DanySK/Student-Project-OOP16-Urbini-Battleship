package tmpmodel;

public class Shot {
    /* lo sparo può esser formato da più punti
       oppure uno sparo di un certo tipo è formato da più spari
       ciò potrebbe portar al problema nel caso una casella sia già stata colpita
       es : WWHHHWW   (water, hit)
       se voglio colpire la prima H con uno sparo da 1, errore
       se voglio colpire WWH con uno sparo da 3, non deve esserci eccezione
   
   */
    private final Point2d point;

    public Shot (Point2d p) {
        if (Ruleset.isPointWithinLimits(p)) {
            this.point = p;
        } else this.point = null;
        // else throw ECCEZIONE
    }

    public Point2d getPoint() {
        return this.point;
    }
}
