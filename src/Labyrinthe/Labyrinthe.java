package Labyrinthe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

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

    // Constante de l'Etat du jeu
    private static final int EN_JEU = -1; 
    private static final int PERDU = 0;
    private static final int GAGNE = 1;

    private boolean isClicked = false;  // Permet de savoir si on a demandé à rejouer en cas de défaite / victoire
    private Teleporteur[] memoireTP;

    // Textures des Cases
    private Image TrouPng;
    private Image PatinoirePng;
    private Image SolPng;
    private Image MurPng;
    private Image TeleporteurPng;
    private Image BouePng;
    private Image BoostHPng;
    private Image BoostBPng;
    private Image BoostDPng;
    private Image BoostGPng;
    private Image SortiePng;
    private Image ObstaclePng;

    /**
     * Construit le labyrinthe à partir d'un fichier Txt
     * Il contient les dimensions du labyrinthe, la disposition de chaque case,
     * et aussi la taille de ces dernières.
     * @param file
     */
    public Labyrinthe(String file) {
        this.TailleCase = 5;
        this.memoireTP = new Teleporteur[10];
        chargerTexture();
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
                        cc = new Teleporteur(l, c, num, TeleporteurPng);
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
                            case '#': cc = new CaseIntraversable(l, c, MurPng); break;
                            case ' ': cc = new CaseOrdinaire(l, c, SolPng); break;
                            case 'S': cc = new Sortie(l, c, SortiePng); break;
                            case 'T': cc = new Trou(l, c, TrouPng); break;
                            case 'b':
                                int r = (int) (this.TailleCase * 0.3f); 
                                this.b = new Bille(l, c, r, this.TailleCase); 
                                cc = new CaseOrdinaire(l, c, SolPng); break;
                            case 'O': cc = new CaseOrdinaire(l, c, new Obstacle(l, c, 10, ObstaclePng), SolPng); break;
                            case 'P': cc = new Patinoire(l, c, PatinoirePng); break;
                            case 'B': cc = new Boue(l, c, BouePng); break;
                            case '^', 'v', '<', '>' : 
                                Direction num = Direction.ofChar(ch);
                                Image sprite = null;
                                switch(num) {
                                    case HAUT: sprite = BoostHPng; break;
                                    case BAS: sprite = BoostBPng; break;
                                    case DROITE: sprite = BoostDPng; break;
                                    case GAUCHE: sprite = BoostGPng; break;
                                }
                                cc = new Tapis(l, c, num, sprite); break;
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

    /**
     * Fonction auxiliaire permettant de charger une unique fois toutes les textures de cases.
     */
    public void chargerTexture() {
        try {
            TrouPng = ImageIO.read(new File("src/img/Trou.png"));
            PatinoirePng = ImageIO.read(new File("src/img/Patinoire.png"));
            SolPng = ImageIO.read(new File("src/img/Sol.png"));
            MurPng = ImageIO.read(new File("src/img/Mur.png"));
            TeleporteurPng = ImageIO.read(new File("src/img/Teleporteur.png"));
            BouePng = ImageIO.read(new File("src/img/Boue.png"));
            BoostHPng = ImageIO.read(new File("src/img/BoostHaut.png"));
            BoostBPng = ImageIO.read(new File("src/img/BoostBas.png"));
            BoostDPng = ImageIO.read(new File("src/img/BoostDroite.png"));
            BoostGPng = ImageIO.read(new File("src/img/BoostGauche.png"));
            SortiePng = ImageIO.read(new File("src/img/Sortie.png"));
            ObstaclePng = ImageIO.read(new File("src/img/Obstacle.png"));
        } catch (IOException e) {
            System.out.println("Image introuvable");
        }
    }

    // Setters
    public void setDefaultfa() {
        this.fa = 0.001;
    }
    public void setfa(double newFa) {
        this.fa = newFa;
    }

    /**
     * Fonction de test, affiche le labyrinthe complet dans la console
     */
    public void affiche() {
        for(int i = 0; i < this.hauteur; i++) {
            for(int j = 0; j < this.largeur ; j++) {
                System.out.print(this.laby[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Dessine chaque case du labyrinthe 1 par 1, dans le JPanel
     */
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
     * Fonction permettant d'obtenir une case du labyrinthe
     * @param y
     * @param x
     * @return
     */
    public Case getCase(int y, int x) {
        if (x >= 0 && x < this.largeur && y >= 0 && y < this.hauteur) {
            return this.laby[y][x];
        }
        return null;
    }

    /**
     * En cas de défaite ou victoire, passe la variable permettant de rejouer à true
     */
    @Override 
    public void mouseClicked(MouseEvent e) {
        if(this.etat != EN_JEU) {
            this.isClicked = true;
        }
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    
    /**
     * Met à une valeur décidé pour les calculs les coordonnées x, y de la souris
     * précédente quand elle sort de l'écran. Afin d'éviter des mouvements quand elle
     * n'y est plus.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.sourisX = -1;
        this.sourisY = -1;
    }

    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    /**
     * Fait avancer la bille en fonction des déplacements de la souris
     * @param e
     */
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
    
    /**
     * Fonction réalisant un tour de jeu, vérifie l'Etat actuel du jeu
     * , les collisions potentiels, ou si la case sort/entre d'une case
     * @return 1 si le jeu continue, -1 si on décide de rejouer, 0 sinon
     */
    public int tour() {
        if (this.etat != EN_JEU) {
            repaint();
            return this.isClicked ? -1 : 0;
        }

        int l = this.b.getLigne();
        int c = this.b.getColonne();

        int coolDown = b.getCooldown();
        // System.out.println(coolDown);
        if(coolDown > 0) {
            b.setCooldown(coolDown - 1);
        }
        
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
