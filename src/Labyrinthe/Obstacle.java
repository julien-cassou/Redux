package Labyrinthe;
import java.awt.Image;
import java.awt.Graphics;
public class Obstacle extends Entite {
    private int res;
    private Image sprite;

    /**
     * Constructeur d'une Entite Obstacle
     * @param l
     * @param c
     * @param r
     * @param sprite
     */
    public Obstacle(int l, int c, int r, Image sprite) {
        super(l, c);
        this.res = r;
        this.sprite = sprite;
    }

    // Getters
    public Image getSprite() {
        return this.sprite;
    }
    public int getRes() {
        return this.res;
    }

    // Méthode qui fait perdre de la Résistance en cas de choc avec la bille
    public void perdResistance() {
        res = res >= 2 ? res - 2 : 0;
    }


    /**
     * @return boolean true si l'obstacle n'a plus de résistance, false sinon
     */
    public boolean estCassé() {
        return this.res <= 0;
    }

    /**
     * Dessine l'Obstacle en fonction de son sprite, ou en une case de couleur prédéfinie 
     * (en fonction de sa résistance via une opacité)
     * @param g
     * @param x
     * @param y
     * @param Taille
     */
    public void dessinerSprite(Graphics g, int x, int y, int Taille) {
        g.drawImage(this.sprite, x, y, Taille, Taille, null);
    }
}

