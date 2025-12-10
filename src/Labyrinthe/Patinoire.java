package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class Patinoire extends CaseOrdinaire {
    public Patinoire(int l, int c) {
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
            l.setfa(0.005);
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
        g.setColor(Color.CYAN);
        g.fillRect(x, y, Taille, Taille);
    }

}
