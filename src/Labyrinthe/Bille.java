package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class Bille extends Entite {
    private double vx, vy;
    private double x, y;
    private int r;
    private int tailleCase;
    private int coolDownTp = 0;  // Variable permettant de gérer les téléportations excessives

    /**
     * Constructeur de la bille, elle stockera ses coordonnées dans la grille, mais aussi
     * dans le tableau Labyrinthe.
     * @param l
     * @param c
     * @param r
     * @param tailleCase
     */
    public Bille(int l, int c, int r, int tailleCase) {
        super(l, c);
        this.vx = 0.0;
        this.vy = 0.0;
        this.r = r;

        this.x = c * tailleCase + (tailleCase/2);
        this.y = l * tailleCase + (tailleCase/2);
        
        this.tailleCase = tailleCase;
    }

    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public int getRayon() {return this.r;}
    public int getCooldown() {return this.coolDownTp;}

    // Setters
    public void inverseVX() { this.vx = this.vx * (-1); }
    public void inverseVY() { this.vy = this.vy * (-1); }
    public void setVY(double vy) {
        this.vy = vy;
    }
    public void setVX(double vx) {
        this.vx = vx;
    }
    public void setX(double x) {
        this.x = x;
        this.setColonne((int) ((this.x - this.r )/ this.tailleCase));
    }
    public void setY(double y) {
        this.y = y;
        this.setLigne((int) ((this.y - this.r )/ this.tailleCase));
    }
    public void setCooldown(int t) {
        this.coolDownTp = t;
    }

    /**
     * Déplace la bille selon sa vitesse et Met à jour la 
     * position précise (double) PUIS la case correspondante (int)
     * @param f
     */
    public void avance(double f) {
        // On limite la vitesse pour ne pas se déplacer de plus d'une 
        // case par tour
        double vMax = this.tailleCase * 0.2;
        if (this.vx > vMax) this.vx = vMax;
        if (this.vx < -vMax) this.vx = -vMax;
        if (this.vy > vMax) this.vy = vMax;
        if (this.vy < -vMax) this.vy = -vMax;

        this.x += this.vx;
        this.y += this.vy;

        // Calculs de diminution par le frottement du déplacement
        double dx, dy;
        if(this.vx == 0 && this.vy == 0) {
            dx = 0; dy = 0;
        }
        else {
            double v = Math.sqrt(this.vx * this.vx + this.vy * this.vy);
            dx = this.vx / v;
            dy = this.vy / v;
        }
        this.vx -= f*dx*this.tailleCase;
        this.vy -= f*dy*this.tailleCase;

        int nouvelleColonne = (int) ((this.x) / this.tailleCase);
        int nouvelleLigne = (int) ((this.y) / this.tailleCase);
        
        this.setColonne(nouvelleColonne);
        this.setLigne(nouvelleLigne);
    }

    /**
     * Fonction permettant l'affichage de la bille dans le JPanel
     * @param g
     */
    public void dessinerBille(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval((int)x - this.r, (int)y - this.r, r*2, r*2);
    }
}