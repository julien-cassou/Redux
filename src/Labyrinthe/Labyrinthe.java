package Labyrinthe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class Labyrinthe extends JPanel implements MouseMotionListener, MouseListener{
    private Case[][] laby;
    private int hauteur, largeur;
    private int TailleCase;
    private Bille b;
    private double sourisX = -1;
    private double sourisY = -1;
    private double fa = 0.001; // Facteur accélération 
    private double f = 0.005; // Frottemment 
    private int etat = -1;

    private static final int EN_JEU = -1;
    private static final int PERDU = 0;
    private static final int GAGNE = 1;
    private boolean isClicked = false;  // Permet de savoir si on a demandé à rejouer en cas de défaite / victoire
    private Teleporteur[] memoireTP;

    public Labyrinthe(String file) {
        this.TailleCase = 5;
        this.memoireTP = new Teleporteur[10];
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
                    if(Character.isDigit(ch)) {
                        int num = Character.getNumericValue(ch);
                        cc = new Teleporteur(c, l, num);
                        if(this.memoireTP[num] == null) {
                            this.memoireTP[num] = (Teleporteur) cc;
                        }
                        else {
                            this.memoireTP[num].setNext((Teleporteur) cc);
                            ((Teleporteur)cc).setNext(this.memoireTP[num]);
                        }
                    }
                    else {
                        switch (ch) {
                            case '#': cc = new CaseIntraversable(l, c); break;
                            case ' ': cc = new CaseOrdinaire(l, c); break;
                            case 'S': cc = new Sortie(l, c); break;
                            case 'T': cc = new Trou(l, c); break;
                            case 'b':
                                int r = (int) (this.TailleCase * 0.3f); 
                                this.b = new Bille(l, c, r, this.TailleCase); 
                                cc = new CaseOrdinaire(l, c); break;
                            case 'O': cc = new CaseOrdinaire(l, c, new Obstacle(l, c, 10)); break;
                            case 'P': cc = new Patinoire(l, c); break;
                            case 'B': cc = new Boue(l, c); break;
                            case '^', 'v', '<', '>' : cc = new Tapis(l, c, Direction.ofChar(ch)); break;
                            default:  cc = null; break;
                        }
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

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
    }

    public void setDefaultfa() {
        this.fa = 0.001;
    }

    public void setfa(double newFa) {
        this.fa = newFa;
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

        if(this.etat == EN_JEU) {
            for(int i = 0; i < this.hauteur; i++) {
                for(int j = 0; j < this.largeur ; j++) {
                    this.laby[i][j].dessinerCase(g, TailleCase);
                }
            }
            this.b.dessinerBille(g);
        }
        else {
            // --- ECRAN DE FIN (GAGNE OU PERDU) ---
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.largeur * TailleCase, this.hauteur * TailleCase);

            // 2. Configuration du texte
            String msg = "";
            Color c = Color.WHITE;
            if(this.etat == PERDU) {
                msg = "PERDU !";
                c = Color.RED;
            }
            else if(this.etat == GAGNE) {
                msg = "GAGNÉ !";
                c = Color.GREEN;
            }
            
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(c);

            // 3. Centrage du texte principal
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(msg)) / 2;
            int y = getHeight() / 2;
            g.drawString(msg, x, y);

            // 4. Petit message pour rejouer
            g.setFont(new Font("Arial", Font.ITALIC, 20));
            fm = g.getFontMetrics();
            g.setColor(Color.WHITE);
            String subMsg = "Cliquez pour recommencer";
            int subX = (getWidth() - fm.stringWidth(subMsg)) / 2;
            g.drawString(subMsg, subX, y + 50);
        }
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

    @Override 
    public void mouseClicked(MouseEvent e) {
        if(this.etat != EN_JEU) {
            this.isClicked = true;
        }
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.sourisX = -1;
        this.sourisY = -1;
    }

    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseMoved(MouseEvent e) {
        int xActuel = e.getX();
        int yActuel = e.getY();
        if(this.sourisX != -1 && this.sourisY != -1.0) {
            double sx = e.getX() - this.sourisX;
            double sy = e.getY() - this.sourisY;

            double ajoutVX = sx * this.fa * this.TailleCase;
            double ajoutVY = sy * this.fa * this.TailleCase;

            this.b.setVX(b.getVx() + ajoutVX);
            this.b.setVY(b.getVy() + ajoutVY);
        }
        this.sourisX = xActuel;
        this.sourisY = yActuel;
    }
    
    public int tour() {
        if (this.etat != EN_JEU) {
            repaint();
            return this.isClicked ? -1 : 0;
        }

        int l = this.b.getLigne();
        int c = this.b.getColonne();
        
        this.b.avance(this.f);

        Case caseBille = this.getCase(l, c);
        caseBille.leave(this.b, this.TailleCase, this);
        caseBille.enter(this.b, this.TailleCase, this);
        try {
            if(!caseBille.touch(b, this.TailleCase, this)) {
                caseBille.touchCoin(b, this.TailleCase, this);
            }
        } catch(NullPointerException e) {
            System.out.println("La case n'existe pas");
        }

        return 1;
    }

    public void setEtat(int n) {
        this.etat = n;
    }
}
