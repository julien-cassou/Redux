package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class Bille extends Entite {
    private double Vx, Vy;

    public Bille(int x, int y) {
        super(x, y);
        this.Vx = 0;
        this.Vy = 0; // Initialisation à 0 pour l'instant, à voir valeur par défault plus tard
    }

    public double getVx() {return this.Vx;}
    public double getVy() {return this.Vy;}

    public void ajouterVX(double v) {
        this.Vx += v;
    }

    public void ajouterVY(double v) {
        this.Vy += v;
    }

    public void dessinerBille(Graphics g, int Taille) {
        int r = (int) (Taille * 0.3f);
        System.out.println(r);
        g.setColor(Color.DARK_GRAY);
        int x = (int)(this.getColonne() * Taille - Taille/2 - r);
        int y = (int)(this.getLigne() * Taille - Taille/2 - r);
        System.out.println(x);
        System.out.println(y);
        g.fillOval(x, y, r, r);
    }
}
