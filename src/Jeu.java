import javax.swing.*;
import javax.swing.Timer;

import Labyrinthe.Labyrinthe;


public class Jeu {
    public static void main(String[] args) throws Exception {
        JFrame j = new JFrame("Jeu");
        Labyrinthe t = new Labyrinthe("Map/laby1.txt");
        j.getContentPane().add(t);
        j.pack();
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        int tempo = 50;
        Timer timer = new javax.swing.Timer(tempo, e -> {
            t.tour();
            j.repaint();
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}
