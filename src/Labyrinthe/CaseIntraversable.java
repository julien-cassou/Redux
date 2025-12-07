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
            g.setColor(Color.BLACK);
            g.fillRect(this.getColonne() * Taille, this.getLigne() * Taille, Taille, Taille);
        }
    }
