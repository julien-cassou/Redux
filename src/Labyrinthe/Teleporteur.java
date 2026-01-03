package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Teleporteur extends CaseOrdinaire {
    private int num;
    private Teleporteur next;

    /**
     * Constructeur d'une Case Téléporteur
     * la lie ensuite plus tard avec un deuxième téléporteur lors 
     * de la création du labyrinthe (1 avec 1, 2 avec 2, etc)
     * @param l
     * @param c
     * @param num
     * @param sprite
     */
    public Teleporteur(int l, int c, int num, Image sprite) {
        super(l, c,sprite);
        this.num = num;
        this.next = null;
    }

    // Setter du téléporteur associé à celui ci
    public void setNext(Teleporteur tp) {
        this.next = tp;
    }

    @Override public boolean isEmpty() { return false;}
    
    /**
     * Fonction qui calcule si la bille est rentré dans la case
     * Teleporteur, la téléporte sur le téléporteur associé si c'est le cas
     * @param b
     * @param Taille
     * @param l
     */
    @Override 
    public void enter(Bille b, int Taille, Labyrinthe l) {
        double bx = b.getX();
        double by = b.getY();
        int ligne = this.getLigne() * Taille;
        int col = this.getColonne() * Taille;
        if(bx > col && bx < col + Taille && by > ligne && by < ligne + Taille) {
            Teleporteur tp = this.next;
            if(tp != null && b.getCooldown() == 0) {
                b.setCooldown(40);
                b.setX(tp.getColonne() * Taille + Taille/2 + b.getRayon());
                b.setY(tp.getLigne() * Taille + Taille/2 + b.getRayon());
            }
        }
    }

    @Override public void leave(Bille b, int Taille, Labyrinthe l) {}

    /**
     * Fonction de test qui permet l'affichage de la case
     * dans la console
     */
    @Override
    public String toString() {
        return String.valueOf(this.num);
    }

    /**
     * Dessine la case en fonction de son sprite, ou en une case de couleur 
     * prédéfinie
     * @param g
     * @param Taille
     */
    @Override
    public void dessinerCase(Graphics g, int Taille) {
        int x = this.getColonne() * Taille;
        int y = this.getLigne() * Taille;

        if(this.getSprite() != null) {
            dessinerSprite(g, x, y, Taille);
        }
        else {
            g.setColor(Color.CYAN);
            g.fillRect(x, y, Taille, Taille);
        }
    }
}
