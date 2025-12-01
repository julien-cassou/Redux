package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
public class CaseIntraversable extends Case {
    
    public CaseIntraversable(int l, int c) {
        super(l,c);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "#";
    }

    @Override
    public void dessinerCase(Graphics g, int Taille) {
        g.setColor(Color.BLACK);
        g.fillRect(this.getColonne() * Taille, this.getLigne() * Taille, Taille, Taille);
    }
}
