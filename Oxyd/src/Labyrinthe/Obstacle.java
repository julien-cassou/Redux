package Labyrinthe;

public class Obstacle extends Entite {
    private int res;

    public Obstacle(int l, int c, int r) {
        super(l, c);
        this.res = r;
    }

    public int getRes() {
        return this.res;
    }

    // Méthode qui fait perdre de la Résistance en cas de choc avec la bille
    public void perdResistance() {
        res = res >= 2 ? res - 2 : 0;
    }
}

