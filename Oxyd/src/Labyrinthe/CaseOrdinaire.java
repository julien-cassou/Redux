package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class CaseOrdinaire extends Case {
    private Entite contenant;

    public CaseOrdinaire(int l, int c) {
        super(l, c);
        this.contenant = null;
    }

    public CaseOrdinaire(int l, int c, Entite e) {
        super(l, c);
        this.contenant = e;
    }

    @Override
    public boolean isEmpty() {
        return this.contenant == null;
    }

    public Entite getEntite() { return this.contenant;}

    @Override 
    public String toString() {
        if(this.contenant instanceof Obstacle) {
            return "O";
        }
        else if(this.contenant instanceof Bille) {
            return "B";
        }
        return " ";
    }

    @Override
    public void dessinerCase(Graphics g, int Taille) {
        if (this instanceof Sortie) {
            g.setColor(Color.BLUE);
        }
        else if(this.contenant instanceof Obstacle) {
            g.setColor(Color.GRAY);
        }
        else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(this.getColonne() * Taille, this.getLigne() * Taille, Taille, Taille);
    }
}
