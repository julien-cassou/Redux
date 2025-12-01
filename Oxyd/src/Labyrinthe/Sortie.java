package Labyrinthe;
import java.awt.Color;
import java.awt.Graphics;

public class Sortie extends CaseOrdinaire {
    public Sortie(int l, int c) {
        super(l, c);
    }

    public Sortie(int l, int c, Entite e) {
        super(l, c, e);
    }

    @Override
    public String toString() {
        return "S";
    }
}
