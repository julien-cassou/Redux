    package Labyrinthe;
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.Image;
    public class CaseIntraversable extends Case {
        
        public CaseIntraversable(int l, int c, Image sprite) {
            super(l,c, sprite);
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void enter(Bille b, int Taille, Labyrinthe l) {}
        
        @Override
        public void leave(Bille b, int Taille, Labyrinthe l) {}

        @Override
        public boolean touch(Bille b, int Taille, Labyrinthe l) { return false;}

        /**
         * @return un booléen true si la case contient un obstacle ou est un mur
         */
        @Override
        public boolean isObstacle() {
            return false;
        }

        @Override
        public void touchCoin(Bille b, int Taille, Labyrinthe l) {}

        @Override
        public String toString() {
            return "#";
        }

        @Override
        public void dessinerCase(Graphics g, int Taille) {
            int x = this.getColonne() * Taille;
            int y = this.getLigne() * Taille;
            if(this.getSprite() != null) {
                dessinerSprite(g, x, y, Taille);
            }
            else {
                g.setColor(new Color(178, 34, 34));
                g.fillRect(x, y, Taille, Taille);
            }
        }
    }
