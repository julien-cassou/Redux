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
        return this.contenant == null || (this.contenant instanceof Bille);
    }

    @Override
    public void enter(Bille b) {}
    
    @Override
    public void leave(Bille b) {}

    @Override
    public void touch(Bille b, int Taille, Labyrinthe l) {
        // Déclarations des variables
        double bx = b.getX();
        double by = b.getY();
        int r = b.getRayon(); 
        double vx = b.getVx();
        double vy = b.getVy();

        int ligneDessin = b.getLigne() * Taille;
        int colonneDessin = b.getColonne() * Taille;
        int ligne = ligneDessin / Taille;
        int colonne = colonneDessin / Taille;


        // 1. Rebonds horizontaux
        // vers la droite
        if(vx > 0 && bx + r > colonneDessin + Taille) {
            Case c1 = l.getCase(ligne, colonne + 1);
            if(c1 instanceof CaseIntraversable || !c1.isEmpty()) {
                System.out.println(c1);
                b.setX(colonneDessin + Taille - r);
                b.inverseVX();
            }
        } 
        // vers la gauche
        else if(vx < 0 && bx - r < colonneDessin) {
            Case c2 = l.getCase(ligne, colonne - 1);
            if(c2 instanceof CaseIntraversable || !c2.isEmpty()) {
                System.out.println(c2);
                b.setX(colonneDessin + r);
                b.inverseVX();
            }
        }
        // 2. Rebonds verticaux
        // vers le bas 
        if(vy > 0 && by + r > ligneDessin + Taille) {
            Case c3 = l.getCase(ligne + 1, colonne);
            if(c3 instanceof CaseIntraversable || !c3.isEmpty()) {
                System.out.println(c3);
                b.setY(ligneDessin + Taille - r);
                b.inverseVY();
            }
        }
        // vers le haut
        if(vy < 0 && by - r < ligneDessin) {
            Case c4 = l.getCase(ligne - 1, colonne);
            if(c4 instanceof CaseIntraversable || !c4.isEmpty()) {
                System.out.println(c4);
                b.setY(ligneDessin + r);
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
