package Labyrinthe;
import java.awt.Graphics;
import java.awt.Image;
public abstract class Case {
    private final int l, c;
    private Image sprite;
    public Case(int l, int c, Image sprite) {
        this.l = l;
        this.c = c;
        this.sprite = sprite;
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

    public Image getSprite() {return this.sprite;}

    abstract public String toString();
    abstract public void dessinerCase(Graphics g, int Taille);

    public void dessinerSprite(Graphics g, int x, int y, int Taille) {
        g.drawImage(this.sprite, x, y, Taille, Taille, null);
    }
}
