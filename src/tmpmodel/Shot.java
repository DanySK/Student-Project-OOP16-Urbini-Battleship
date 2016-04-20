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

    public Shot (Point2d point) {
        this.point = point;
    }

    public Point2d getPoint() {
        return this.point;
    }
}
