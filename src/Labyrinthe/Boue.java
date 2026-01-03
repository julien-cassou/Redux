package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Boue extends CaseOrdinaire {
    /**
     * Constructeur de la Case de type Boue
     * @param l
     * @param c
     * @param sprite
     */
    public Boue(int l, int c, Image sprite) {
        super(l, c, sprite);
    }

    @Override public boolean isEmpty() { return false;}

    
    @Override
    /**
     * Fonction qui calcule si la bille est rentré dans la case
     * boueuse, décrémente ainsi sa vitesse et son facteur d'accélération
     * @param b
     * @param Taille
     * @param l
     */
    public void enter(Bille b, int Taille, Labyrinthe l) {
        double bx = b.getX();
        double by = b.getY();

        int ligne = this.getLigne() * Taille;
        int col = this.getColonne() * Taille;

        if(bx > col && bx < col + Taille && by > ligne && by < ligne + Taille) {
            l.setfa(0.0005);

            // Pour faire l'effet du freinage si la vitesse trop grande
            b.setVX(b.getVx() * 0.70);
            b.setVY(b.getVy() * 0.70);
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
     * Fonction de test pour afficher la case dans la console
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
            g.setColor(new Color(139, 69, 19));
            g.fillRect(x, y, Taille, Taille);
        }
    }
}

