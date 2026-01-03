package Labyrinthe;
import java.awt.Graphics;
import java.awt.Image;
public abstract class Case {
    private final int l, c;
    private Image sprite;

    /**
     * Constructeur d'une Case classique
     * @param l
     * @param c
     * @param sprite
     */
    public Case(int l, int c, Image sprite) {
        this.l = l;
        this.c = c;
        this.sprite = sprite;
    }

    // Fonctions abstract à déclarer dans les classes enfants
    abstract public boolean isEmpty();
    abstract public void enter(Bille b, int Taille, Labyrinthe l);
    abstract public void leave(Bille b, int Taille, Labyrinthe l);
    abstract public boolean touch(Bille b, int Taille, Labyrinthe l);
    abstract public boolean isObstacle();
    abstract public void touchCoin(Bille b, int Taille, Labyrinthe l);
    abstract public String toString();
    abstract public void dessinerCase(Graphics g, int Taille);

    // Getters
    public int getLigne() {
        return this.l;
    }
    public int getColonne() {
        return this.c;
    }
    public Image getSprite() {return this.sprite;}

    /**
     * Dessine la case en fonction de son sprite, ou en une case de couleur prédéfinie
     * @param g
     * @param Taille
     */
    public void dessinerSprite(Graphics g, int x, int y, int Taille) {
        g.drawImage(this.sprite, x, y, Taille, Taille, null);
    }
}
