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
        if (this.contenant == null || this.contenant instanceof Bille) {
            return; 
        }
        // Déclaration des variables 
        int r = b.getRayon();
        int x = b.getColonne(); 
        int y = b.getLigne();
        int caseX = this.getColonne() * Taille; 
        int caseY = this.getLigne() * Taille;

        double vx = b.getVx();
        double vy = b.getVy();

        if(x + r > caseX + Taille) {
            // System.out.println("Touché case devant");
            if(vx > 0) {
                b.setColonne((int) (caseX - r));
                b.inverseVX();
            }
        }
        if(x - r < caseX) {
            // System.out.println("Touché case derriere");
            if(vx < 0) {
                b.setColonne((int)(caseX + r));
                b.inverseVX();
            }
        }
        if(y + r > caseY + Taille) {
            // System.out.println("Touché case dessous");
            if(vy > 0) {
                b.setLigne((int) (caseY - r));
                b.inverseVY();
            }
        }
        if(y - r < caseY) {
            // System.out.println("Touché case dessus");
            if(vy < 0) {
                b.setLigne((int) (caseY + r));
                b.inverseVY();
            }
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
