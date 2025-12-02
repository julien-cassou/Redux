package Labyrinthe;
import java.awt.Graphics;
public abstract class Case {
    private final int l, c;
    public Case(int l, int c) {
        this.l = l;
        this.c = c;
    }

    abstract public boolean isEmpty();
    abstract public void enter(Bille b);
    abstract public void leave(Bille b);
    abstract public void touch(Bille b, int Taille);

    public int getLigne() {
        return this.l;
    }

    public int getColonne() {
        return this.c;
    }

    abstract public String toString();
    abstract public void dessinerCase(Graphics g, int Taille);
}
