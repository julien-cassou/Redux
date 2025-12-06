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
                        case 'S': cc = new Sortie(l, c); System.out.println("Ligne : " + l + " Colonne : " + c); break;
                        
                        case 'B':
                            int r = (int) (this.TailleCase * 0.3f); 
                            this.b = new Bille(l, c, r, this.TailleCase); 
                            cc = new CaseOrdinaire(l, c, this.b); break;
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


    /**
     * Fonction qui retourne la case du plateau
     * des coords données
     */
    public Case getCase(int y, int x) {
        if (x >= 0 && x < this.largeur && y >= 0 && y < this.hauteur) {
            return this.laby[y][x];
        }
        return null;
    }
    
    public void tour() {
        this.b.avance();
        
        int l = this.b.getLigne();
        int c = this.b.getColonne();

        Case caseBille = this.getCase(l, c);
        try {
            caseBille.touch(b, this.TailleCase, this);
        } catch(NullPointerException e) {
            System.out.println("La case n'existe pas");
        }
        
        // verifierCollision(l - 1, c); // Voisin Haut
        // verifierCollision(l + 1, c); // Voisin Bas
        // verifierCollision(l, c - 1); // Voisin Gauche
        // verifierCollision(l, c + 1); // Voisin Droite
        
        // Voisins en diagonale pour les coins parfaits
        // verifierCollision(l - 1, c - 1);
        // verifierCollision(l - 1, c + 1);
        // verifierCollision(l + 1, c - 1);
        // verifierCollision(l + 1, c + 1);
    }

    // Petite fonction pour éviter de copier-coller le if partout
    // private void verifierCollision(int l, int c) {
    //     Case caseVoisine = getCase(c, l);

    //     if (caseVoisine == null) {
    //         return;
    //     }
    //     if (caseVoisine instanceof CaseIntraversable || !caseVoisine.isEmpty()) {
    //         caseVoisine.touch(this.b, this.TailleCase);
    //     }
    // }
}
