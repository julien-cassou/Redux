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

    @Override
    public void enter(Bille b) {}
    
    @Override
    public void leave(Bille b) {}

    @Override
    public void touch(Bille b, int Taille) {
        // Déclaration des variables 
        int r = b.getRayon();
        int x = b.getColonne(); int y = b.getLigne();
        int i = this.getColonne(); int j = this.getLigne();

        if(x + r > i * Taille + Taille) {
            System.out.println("Touché case devant");
        }
        if(x - r < i) {
            System.out.println("Touché case derriere");
        }
        if(y + r > j * Taille + Taille) {
            System.out.println("Touché case dessous");
        }
        if(y - r < j) {
            System.out.println("Touché case dessus");
        }
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
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;
        
        if (this instanceof Sortie) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, Taille, Taille);
        }
        else if(this.contenant instanceof Obstacle) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, Taille, Taille);
        }
        else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, Taille, Taille);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, Taille - 1, Taille - 1);
        }
    }
}
