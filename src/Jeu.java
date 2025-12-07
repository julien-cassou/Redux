import javax.swing.*;
import Interface.Interface;

public class Jeu {
    public static void main(String[] args) {
        JFrame j = new JFrame("Jeu");
        Interface i = new Interface(j);
        
        j.add(i);
        
        j.pack();
        j.setSize(800, 600);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}