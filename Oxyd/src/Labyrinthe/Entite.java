package Labyrinthe;
public class Entite {
    private int l, c;

    public Entite(int l, int c) {
        this.l = l;
        this.c = c;
    }

    public int getLigne() {
        return this.l;
    }

    public int getColonne() {
        return this.c;
    }

    public void setLigne(int l) {
        this.l = l;
    }

    public void setColonne(int c) {
        this.c = c;
    }
}
