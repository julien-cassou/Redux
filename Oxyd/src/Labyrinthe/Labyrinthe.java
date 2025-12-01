package Labyrinthe;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class Labyrinthe {
    private Case[][] laby;
    private int hauteur, largeur;

    public Labyrinthe(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream("src/" + file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
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
    }

    public void affiche() {
        for(int i = 0; i < this.hauteur; i++) {
            for(int j = 0; j < this.largeur ; j++) {
                System.out.print(this.laby[i][j]);
            }
            System.out.println();
        }
    }
}
