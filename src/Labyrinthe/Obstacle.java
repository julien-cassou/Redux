package Labyrinthe;
import java.awt.Image;
import java.awt.Graphics;
public class Obstacle extends Entite {
    private int res;
    private Image sprite;

    public Obstacle(int l, int c, int r, Image sprite) {
        super(l, c);
        this.res = r;
        this.sprite = sprite;
    }

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

    public void dessinerSprite(Graphics g, int x, int y, int Taille) {
        g.drawImage(this.sprite, x, y, Taille, Taille, null);
    }
}

