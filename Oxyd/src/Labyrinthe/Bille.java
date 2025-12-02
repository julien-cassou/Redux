package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class Bille extends Entite {
    private double Vx, Vy;
    private int r;

    public Bille(int x, int y, int r, int Taille) {
        super((int)((x * Taille + Taille/2) - r/2), (int)((y * Taille + Taille/2) - r/2));
        this.Vx = 0;
        this.Vy = 0; // Initialisation à 0 pour l'instant, à voir valeur par défault plus tard
        this.r = r;
    }

    public double getVx() {return this.Vx;}
    public double getVy() {return this.Vy;}
    public int getRayon() {return this.r;}

    /**
     * Augmente la vélocité horizontale
     */
    public void ajouterVX(double v) {
        this.Vx += v;
    }

    /**
     * Augmente la vélocité verticale
     */
    public void ajouterVY(double v) {
        this.Vy += v;
    }

    /**
     * Affiche la bille au centre de sa case
     * La taille du cercle est proportionnelle à la taille d'une case (60%)
     */
    public void dessinerBille(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval(this.getColonne(), this.getLigne(), r, r);
    }

    /**
     * Déplace la bille selon sa vélocité (Vx, Vy)
     * TODO: Ajouter des contrôles de collision avec les murs
     */
    public void avance() {
        int x = this.getColonne();
        int y = this.getLigne();
        x += this.Vx;
        y += this.Vy;
        this.setColonne(x);
        this.setLigne(y);
    }
}
