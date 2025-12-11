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
        // 1. DESSINER LE FOND
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;

        if(this.getSprite() != null) {
            dessinerSprite(g, x, y, Taille);
        }
        else {
            g.setColor(Color.LIGHT_GRAY); // Un gris pour faire "métal"
            g.fillRect(x, y, Taille, Taille);
            
            // 2. DESSINER LA FLÈCHE
            g.setColor(Color.BLACK); // Couleur de la flèche
            
            // On calcule le centre de la case
            int cx = x + Taille / 2;
            int cy = y + Taille / 2;
            // Taille de la flèche (un tiers de la case)
            int size = Taille / 3; 
    
            // On dessine selon la direction
            // Polygon(TableauX, TableauY, nombreDePoints)
            switch(this.d) {
                case HAUT:
                    // Tige
                    g.drawLine(cx, cy + size, cx, cy - size);
                    // Pointe (Triangle) : Centre-Haut, Gauche, Droite
                    g.fillPolygon(new int[]{cx, cx - size/2, cx + size/2}, 
                                new int[]{cy - size, cy, cy}, 3);
                    break;
                    
                case BAS:
                    // Tige
                    g.drawLine(cx, cy - size, cx, cy + size);
                    // Pointe : Centre-Bas, Gauche, Droite
                    g.fillPolygon(new int[]{cx, cx - size/2, cx + size/2}, 
                                new int[]{cy + size, cy, cy}, 3);
                    break;
                    
                case GAUCHE:
                    // Tige
                    g.drawLine(cx + size, cy, cx - size, cy);
                    // Pointe : Centre-Gauche, Haut, Bas
                    g.fillPolygon(new int[]{cx - size, cx, cx}, 
                                new int[]{cy, cy - size/2, cy + size/2}, 3);
                    break;
                    
                case DROITE:
                    // Tige
                    g.drawLine(cx - size, cy, cx + size, cy);
                    // Pointe : Centre-Droite, Haut, Bas
                    g.fillPolygon(new int[]{cx + size, cx, cx}, 
                                new int[]{cy, cy - size/2, cy + size/2}, 3);
                    break;
            }
        }
    }
}
