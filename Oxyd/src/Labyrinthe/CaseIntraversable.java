package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
public class CaseIntraversable extends Case {
    
    public CaseIntraversable(int l, int c) {
        super(l,c);
    }

    @Override
    public boolean isEmpty() {
        return false;
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

    @Override
    public String toString() {
        return "#";
    }

    @Override
    public void dessinerCase(Graphics g, int Taille) {
        g.setColor(Color.BLACK);
        g.fillRect(this.getColonne() * Taille, this.getLigne() * Taille, Taille, Taille);
    }
}
