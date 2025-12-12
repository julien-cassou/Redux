package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class CaseOrdinaire extends Case {
    private Entite contenant;

    public CaseOrdinaire(int l, int c, Image sprite) {
        super(l, c, sprite);
        this.contenant = null;
    }

    public CaseOrdinaire(int l, int c, Entite e,  Image sprite) {
        super(l, c, sprite);
        this.contenant = e;
    }


    @Override
    public boolean isEmpty() {
        return this.contenant == null || (this.contenant instanceof Bille);
    }

    @Override
    public void enter(Bille b, int Taille, Labyrinthe l) {}
    
    @Override
    public void leave(Bille b, int Taille, Labyrinthe l) {}

    @Override
    public boolean touch(Bille b, int Taille, Labyrinthe l) {
        // Déclarations des variables
        double bx = b.getX();
        double by = b.getY();
        int r = b.getRayon(); 
        double vx = b.getVx();
        double vy = b.getVy();

        boolean rebond = false;

        int ligneDessin = b.getLigne() * Taille;
        int colonneDessin = b.getColonne() * Taille;
        int ligne = ligneDessin / Taille;
        int colonne = colonneDessin / Taille;


        // 1. Rebonds horizontaux
        // vers la droite
        if(vx > 0 && bx + r > colonneDessin + Taille) {
            Case c1 = l.getCase(ligne, colonne + 1);
            if(c1 instanceof CaseIntraversable || c1.isObstacle()) {
                b.setX(colonneDessin + Taille - r);
                b.inverseVX();
                rebond = true;
                this.enleveResistance(c1);
            }
        } 
        // vers la gauche
        else if(vx < 0 && bx - r < colonneDessin) {
            Case c2 = l.getCase(ligne, colonne - 1);
            if(c2 instanceof CaseIntraversable || c2.isObstacle()) {
                b.setX(colonneDessin + r);
                b.inverseVX();
                rebond = true;
                this.enleveResistance(c2);
            }
        }
        // 2. Rebonds verticaux
        // vers le bas 
        if(vy > 0 && by + r > ligneDessin + Taille) {
            Case c3 = l.getCase(ligne + 1, colonne);
            if(c3 instanceof CaseIntraversable || c3.isObstacle()) {
                b.setY(ligneDessin + Taille - r);
                b.inverseVY();
                rebond = true;
                this.enleveResistance(c3);
            }
        }
        // vers le haut
        if(vy < 0 && by - r < ligneDessin) {
            Case c4 = l.getCase(ligne - 1, colonne);
            if(c4 instanceof CaseIntraversable || c4.isObstacle()) {
                b.setY(ligneDessin + r);
                b.inverseVY();
                rebond = true;
                this.enleveResistance(c4);
            }
        }

        return rebond;
    }

    @Override
    public void touchCoin(Bille b, int Taille, Labyrinthe l) {
        // Déclarations des variables
        double bx = b.getX();
        double by = b.getY();
        int r = b.getRayon(); 
        double vx = b.getVx();
        double vy = b.getVy();

        int lig = b.getLigne();
        int col = b.getColonne();

        int v = lig * Taille;
        int v1 = v + Taille;
        int u = col * Taille;
        int u1 = u + Taille;
        
        // Coin Haut gauche
        if(Math.sqrt((bx - u) * (bx - u) + (by - v) * (by - v)) < r) {
            Case c1 = l.getCase(lig - 1, col - 1);
            if(c1 != null && (c1  instanceof CaseIntraversable || c1.isObstacle())) {
                calculerRebondCoin(b, bx, by, vx, vy, u, v);
                this.enleveResistance(c1);
                return;
            }
        }
        // Coin Haut droite 
        if(Math.sqrt((bx - u1) * (bx - u1) + (by - v) * (by - v)) < r) {
            Case c2 = l.getCase(lig - 1, col + 1);
            if(c2 != null && (c2  instanceof CaseIntraversable || c2.isObstacle())) {
                calculerRebondCoin(b, bx, by, vx, vy, u1, v);
                this.enleveResistance(c2);
                return;
            }
        }
        // Coin Bas gauche
        if(Math.sqrt((bx - u) * (bx - u) + (by - v1) * (by - v1)) < r) {
            Case c3 = l.getCase(lig + 1, col - 1);
            if(c3 != null && (c3  instanceof CaseIntraversable || c3.isObstacle())) {
                calculerRebondCoin(b, bx, by, vx, vy, u, v1);
                this.enleveResistance(c3);
                return;
            }   
        }
        // Coin Bas droit
        if(Math.sqrt((bx - u1) * (bx - u1) + (by - v1) * (by - v1)) < r) {
            Case c4 = l.getCase(lig + 1, col + 1);
            if(c4 != null && (c4  instanceof CaseIntraversable || c4.isObstacle())) {
                calculerRebondCoin(b, bx, by, vx, vy, u1, v1);
                this.enleveResistance(c4);
                return;
            }
        }
    }
    
    /**
     * fonction qui applique les formules pour calculer la vitesse suite à un rebord sur un coin.
     * @param b la bille 
     * @param bx coords x de la bille
     * @param by coords y de la bille
     * @param vx vitesse horizontale de la bille
     * @param vy vitesse verticale de la bille
     * @param u coord x du coin de la case 
     * @param v coord y du coin de la case
     */
    public void calculerRebondCoin(Bille b, double bx, double by, double vx, double vy, double u, double v) {
        double rc = Math.sqrt((bx - u) * (bx - u) + (by - v) * (by - v));
        
        double dcx = (bx -u) / rc;
        double dcy = (by - v) / rc;

        double vcoin = vx*dcx + vy*dcy;

        // On ne rebondit que si on se rapproche du mur / Obstacle
        if (vcoin < 0) {
            b.setVX(vx - 2 * vcoin * dcx);
            b.setVY(vy - 2 * vcoin * dcy);
        }
    }
    
    /**
     * @return un booléen true si la case contient un obstacle ou est un mur
     */
    public boolean isObstacle() {
        return (this.contenant instanceof Obstacle);
    }

    /**
     * Applique des dégâts à l'obstacle contenu dans la case c
     */
    public void enleveResistance(Case c) {
        if (c instanceof CaseOrdinaire) {
            CaseOrdinaire caseCible = (CaseOrdinaire) c;
            
            if (caseCible.getEntite() instanceof Obstacle) {
                Obstacle obs = (Obstacle) caseCible.getEntite();
                obs.perdResistance(); 
                
                if (obs.estCassé()) {
                    caseCible.setEntite(null); 
                }
            }
        }
    }

    // Ajoute ce setter dans CaseOrdinaire si tu ne l'as pas encore :
    public void setEntite(Entite e) {
        this.contenant = e;
    }


    public Entite getEntite() { return this.contenant;}


    @Override 
    public String toString() {
        if(this.contenant instanceof Obstacle) {
            return "O";
        }
        else if(this.contenant instanceof Bille) {
            return "B";
        }
        return " ";
    }

    @Override
    public void dessinerCase(Graphics g, int Taille) {
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;
        
        if(this.contenant instanceof Obstacle) {
            Obstacle o = (Obstacle)this.contenant;
            if(o.getSprite() != null) {
                o.dessinerSprite(g, x, y, Taille);
            }
            else { 
                int res = ((Obstacle)this.contenant).getRes();
                if(res >= 10) g.setColor(Color.DARK_GRAY);
                else if(res == 8) g.setColor(Color.GRAY);
                else if(res == 6) g.setColor(Color.LIGHT_GRAY);
                else if(res <= 4) g.setColor(new Color(220, 220, 220));
                else if(res == 0) g.setColor(Color.WHITE); 
                g.fillRect(x, y, Taille, Taille);

                // Bordure pour bien distinguer les briques
                g.setColor(Color.BLACK);
                g.drawRect(x, y, Taille - 1, Taille - 1);
            }
        }
        else {
            if(this.getSprite() != null) {
                dessinerSprite(g, x, y, Taille);
            }
            else {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, Taille, Taille);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, Taille - 1, Taille - 1);
            }
        }
    }
}
