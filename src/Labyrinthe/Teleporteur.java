package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Teleporteur extends CaseOrdinaire {
    private int num;
    private Teleporteur next;

    public Teleporteur(int l, int c, int num, Image sprite) {
        super(l, c,sprite);
        this.num = num;
        this.next = null;
    }

    public void setNext(Teleporteur tp) {
        this.next = tp;
    }

    @Override public boolean isEmpty() { return false;}
    
    @Override 
    public void enter(Bille b, int Taille, Labyrinthe l) {
        double bx = b.getX();
        double by = b.getY();
        int ligne = this.getLigne() * Taille;
        int col = this.getColonne() * Taille;
        if(bx > col && bx < col + Taille && by > ligne && by < ligne + Taille) {
            Teleporteur tp = this.next;
            if(tp != null && b.getCooldown() == 0) {
                b.setCooldown(40);
                b.setX(tp.getColonne() * Taille + Taille/2 + b.getRayon());
                b.setY(tp.getLigne() * Taille + Taille/2 + b.getRayon());
            }
        }
    }

    @Override public void leave(Bille b, int Taille, Labyrinthe l) {}

    @Override
    public String toString() {
        return String.valueOf(this.num);
    }

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
