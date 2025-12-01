package Labyrinthe;

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
}
