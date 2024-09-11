package src;

public class Des {
    private static final int NBRE_FACES = 6;
    private int face = 0;

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public void Lance() {
        face = (int) (Math.random() * NBRE_FACES + 1);
    }

}
