package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Tapis extends CaseOrdinaire {
    private Direction d;
    private double vx, vy;

    public Tapis(int l, int c, Direction d, Image sprite) {
        super(l, c, sprite);
        this.d = d;

        switch(d) {
            case GAUCHE : 
                this.vx = -10.0;
                this.vy = 0;
                break;
            case DROITE : 
                this.vx = 10.0;
                this.vy = 0;
                break;
            case HAUT : 
                this.vx = 0;
                this.vy = -10.0;
                break;
            case BAS : 
                this.vx = 0;
                this.vy = 10.0;
                break;
        }
    }

    @Override public boolean isEmpty() { return false;}

    @Override 
    public void enter(Bille b, int Taille, Labyrinthe l) {
        double bx = b.getX();
        double by = b.getY();

        int ligne = this.getLigne() * Taille;
        int col = this.getColonne() * Taille;

        if(bx > col && bx < col + Taille && by > ligne && by < ligne + Taille) {
            b.setVX(this.vx);
            b.setVY(this.vy);
        }
    }

    @Override
    public String toString() {
        switch(d) {
            case GAUCHE : 
                return "<";
            case DROITE : 
                return ">";
            case HAUT : 
                return "^";
            case BAS : 
                return "v";
            default : return "<";
        }
    }

    @Override
    public void dessinerCase(Graphics g, int Taille) {
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;

        if(this.getSprite() != null) {
            dessinerSprite(g, x, y, Taille);
        }
        else {
            g.setColor(Color.LIGHT_GRAY); // Un gris pour faire "métal"
            g.fillRect(x, y, Taille, Taille);
            
            // dessin de la flèche
            g.setColor(Color.BLACK);
            
            // On calcule le centre de la case
            int cx = x + Taille / 2;
            int cy = y + Taille / 2;
    
            // On dessine selon la direction
            switch(this.d) {
                case HAUT:
                    g.drawLine(cx, cy - 3, cx, cy + 10);
                    break;
                    
                case BAS:
                    g.drawLine(cx, cy + 3, cx, cy - 10);
                    break;
                    
                case GAUCHE:
                    g.drawLine(cx - 3, cy, cx + 10, cy);
                    break;
                    
                case DROITE:
                    g.drawLine(cx + 3, cy, cx - 10, cy);
                    break;
            }
        }
    }
}
