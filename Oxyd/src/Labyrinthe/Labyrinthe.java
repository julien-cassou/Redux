package Labyrinthe;

import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;

public class Labyrinthe extends JPanel {
    private Case[][] laby;
    private int hauteur, largeur;
    private int TailleCase;
    private Bille b;
    private Case caseBille;

    public Labyrinthe(String file) {
        this.TailleCase = 5;
        try {
            Scanner sc = new Scanner(new FileInputStream("src/" + file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            this.TailleCase = sc.nextInt();
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
                        
                        case 'B':
                            int r = (int) (this.TailleCase * 0.6f); 
                            this.b = new Bille(l, c, r, this.TailleCase); 
                            cc = new CaseOrdinaire(l, c, this.b); 
                            this.caseBille = cc; break;
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
        this.b.dessinerBille(g);
    }

    public void test() {
        this.caseBille.touch(this.b, this.TailleCase);
    }
}
