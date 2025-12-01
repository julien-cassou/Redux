package Labyrinthe;

import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;

public class Labyrinthe extends JPanel {
    private Case[][] laby;
    private int hauteur, largeur;
    final int TailleCase;

    public Labyrinthe(String file) {
        int tempTaille = 0;
        try {
            Scanner sc = new Scanner(new FileInputStream("src/" + file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            tempTaille = sc.nextInt();
            sc.nextLine();
            this.laby = new Case[hauteur][largeur];
            for (int l = 0; l < this.hauteur; l++) {
                String line = sc.nextLine();
                for (int c = 0; c < this.largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new CaseIntraversable(l, c); break;
                        case ' ': cc = new CaseOrdinaire(l, c); break;
                        case 'S': cc = new Sortie(l, c); break;
                        
                        case 'B': cc = new CaseOrdinaire(l, c, new Bille(l, c)); break;
                        case 'O': cc = new CaseOrdinaire(l, c, new Obstacle(l, c, 10)); break;
                        default:  cc = null; break;
                    }
                    this.laby[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { 
            e.printStackTrace(); 
        }
        this.TailleCase = tempTaille;
        this.setPreferredSize(new Dimension(this.largeur * TailleCase, this.hauteur * TailleCase));
    }

    
    /** Fonction affiche
     *  Elle permet d'afficher dans la console le Labyrinthe 
     */
    public void affiche() {
        for(int i = 0; i < this.hauteur; i++) {
            for(int j = 0; j < this.largeur ; j++) {
                System.out.print(this.laby[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < this.hauteur; i++) {
            for(int j = 0; j < this.largeur ; j++) {
                this.laby[i][j].dessinerCase(g, TailleCase);
            }
        }
    }
}
