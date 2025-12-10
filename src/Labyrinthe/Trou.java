package Labyrinthe;

import java.awt.Color;
import java.awt.Graphics;

public class Trou extends Case {
    public Trou(int l, int c) {
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
            b = null;
            l.setEtat(0);
        }
    }

    @Override public void leave(Bille b, int Taille, Labyrinthe l) {}
    @Override public boolean touch(Bille b, int Taille, Labyrinthe l) { return false;}
    @Override public boolean isObstacle() { return false;}
    @Override public void touchCoin(Bille b, int Taille, Labyrinthe l) {}

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public void dessinerCase(Graphics g, int Taille) {
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;
        g.setColor(Color.BLACK);
        g.fillRect(x, y, Taille, Taille);
    }
}

