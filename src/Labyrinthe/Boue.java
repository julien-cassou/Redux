package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class Boue extends CaseOrdinaire {
    public Boue(int l, int c) {
        super(l, c);
    }

    @Override public boolean isEmpty() { return false;}

    @Override 
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

    @Override public void leave(Bille b, int Taille, Labyrinthe l) {
        double bx = b.getX();
        double by = b.getY();

        int ligne = this.getLigne() * Taille;
        int col = this.getColonne() * Taille;

        if(bx <= col || bx >= col + Taille || by <= ligne || by >= ligne + Taille) {
            l.setDefaultfa();
        }
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public void dessinerCase(Graphics g, int Taille) {
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;
        g.setColor(new Color(139, 69, 19));
        g.fillRect(x, y, Taille, Taille);
    }

}

