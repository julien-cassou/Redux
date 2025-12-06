package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class Bille extends Entite {
    private double vx, vy;
    private double x, y;
    private int r;
    private int tailleCase;

    public Bille(int l, int c, int r, int tailleCase) {
        super(l, c);
        this.vx = 0.00 * tailleCase;
        this.vy = 0.05 * tailleCase;
        this.r = r;

        this.x = c * tailleCase + (tailleCase/2);
        this.y = l * tailleCase + (tailleCase/2);
        
        this.tailleCase = tailleCase;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public int getRayon() {return this.r;}

    public void inverseVX() { this.vx = this.vx * (-1); }
    public void inverseVY() { this.vy = this.vy * (-1); }
    
    public void setX(double x) {
        this.x = x;
        this.setColonne((int) ((this.x - this.r )/ this.tailleCase));
    }

    public void setY(double y) {
        this.y = y;
        this.setLigne((int) ((this.y - this.r )/ this.tailleCase));
    }
    /**
     * Déplace la bille selon sa vitesse
     * Met à jour la position précise (double) PUIS la case correspondante (int)
     */
    public void avance() {
        this.x += this.vx;
        this.y += this.vy;

        int nouvelleColonne = (int) ((this.x) / this.tailleCase);
        int nouvelleLigne = (int) ((this.y) / this.tailleCase);
        
        // System.out.println("bx : " + x + " by : " + y + "; ix : " + nouvelleColonne + " iy : " + nouvelleLigne);
        this.setColonne(nouvelleColonne);
        this.setLigne(nouvelleLigne);
    }

    public void dessinerBille(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval((int)x - this.r, (int)y - this.r, r*2, r*2);
    }
}