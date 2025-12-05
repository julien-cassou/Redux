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

            double bx = b.getX();
            double by = b.getY();
            double r = b.getRayon(); 
            
            // Coordonnées du Mur
            double murX = this.getColonne() * Taille;
            double murY = this.getLigne() * Taille;

            double vx = b.getVx();
            double vy = b.getVy();

            // 1. Choc Horizontal (Gauche / Droite)
            // Si la bille arrive par la gauche et tape le bord gauche du mur
            if (vx > 0 && bx + r > murX && bx < murX) {
                b.setX(murX - r); // On la recolle au bord
                b.inverseVX();
            }
            // Si la bille arrive par la droite et tape le bord droit du mur
            else if (vx < 0 && bx - r < murX + Taille && bx > murX + Taille) {
                b.setX(murX + Taille + r);
                b.inverseVX();
            }

            // 2. Choc Vertical (Haut / Bas)
            // Si la bille arrive du haut
            if (vy > 0 && by + r > murY && by < murY) {
                b.setY(murY - r);
                b.inverseVY();
            }
            // Si la bille arrive du bas
            else if (vy < 0 && by - r < murY + Taille && by > murY + Taille) {
                System.out.println("On y est");
                b.setY(murY + Taille + r);
                b.inverseVY();
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
