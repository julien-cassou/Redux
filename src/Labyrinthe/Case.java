package Labyrinthe;
import java.awt.Graphics;
public abstract class Case {
    private final int l, c;
    public Case(int l, int c) {
        this.l = l;
        this.c = c;
    }

    abstract public boolean isEmpty();
    abstract public void enter(Bille b, int Taille, Labyrinthe l);
    abstract public void leave(Bille b, int Taille, Labyrinthe l);
    abstract public boolean touch(Bille b, int Taille, Labyrinthe l);
    abstract public boolean isObstacle();
    abstract public void touchCoin(Bille b, int Taille, Labyrinthe l);

    public int getLigne() {
        return this.l;
    }

    public int getColonne() {
        return this.c;
    }

    abstract public String toString();
    abstract public void dessinerCase(Graphics g, int Taille);
}
