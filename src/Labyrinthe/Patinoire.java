package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Patinoire extends CaseOrdinaire {
    /**
     * Construteur de la Case type Patinoire
     * @param l
     * @param c
     * @param sprite
     */
    public Patinoire(int l, int c, Image sprite) {
        super(l, c, sprite);
    }

    @Override public boolean isEmpty() { return false;}

    /**
     * Fonction qui calcule si la bille est rentré dans la case
     * patinoire, incrémente ainsi son facteur d'accélération
     * @param b
     * @param Taille
     * @param l
     */
    @Override 
    public void enter(Bille b, int Taille, Labyrinthe l) {
        double bx = b.getX();
        double by = b.getY();

        int ligne = this.getLigne() * Taille;
        int col = this.getColonne() * Taille;

        if(bx > col && bx < col + Taille && by > ligne && by < ligne + Taille) {
            l.setfa(0.005);
        }
    }

    /**
     * Similaire à la fonction enter, mais réinitialise le facteur d'accélération
     * @param b
     * @param Taille
     * @param l
     */
    @Override public void leave(Bille b, int Taille, Labyrinthe l) {
        double bx = b.getX();
        double by = b.getY();

        int ligne = this.getLigne() * Taille;
        int col = this.getColonne() * Taille;

        if(bx <= col || bx >= col + Taille || by <= ligne || by >= ligne + Taille) {
            l.setDefaultfa();
        }
    }

    /**
     * Fonction de test permettant l'affichage de la case dans la console
     */
    @Override
    public String toString() {
        return "P";
    }

    /**
     * Dessine la case en fonction de son sprite, ou en une case de couleur prédéfinie
     * @param g
     * @param Taille
     */
    @Override
    public void dessinerCase(Graphics g, int Taille) {
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;

        if(this.getSprite() != null) {
            dessinerSprite(g, x, y, Taille);
        }
        else {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, Taille, Taille);
        }
    }
}
