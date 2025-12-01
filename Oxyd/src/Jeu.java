import javax.swing.*;

import Labyrinthe.Labyrinthe;


public class Jeu {
    public static void main(String[] args) throws Exception {
        JFrame j = new JFrame("Jeu");
        Labyrinthe t = new Labyrinthe("Map/laby1.txt");
        j.getContentPane().add(t);
        j.pack();
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // t.affiche();
    }
}
