    package Interface;

    import java.awt.*;
    import java.awt.event.*;

    import javax.swing.*;
    import Labyrinthe.Labyrinthe;

    public class Interface extends JPanel {
        
        private JFrame frame;
        private String[] paths = {"Map/laby1.txt", "Map/laby2.txt", "Map/laby3.txt"};
        
        private Labyrinthe actu = null;
        private Timer timer;

        /**
         * Créer une nouvelle interface en la liant avec un JFrame
         * @param frame
         */
        public Interface(JFrame frame) {
            this.frame = frame;
            reconstruireInterface();
        }

        /**
         * Fonction auxiliaire qui créer les boutons permettant de lancer un niveau précis
         * @param nom
         * @param cheminImage
         * @param indice
         * @return
         */
        private JButton creerBouton(String nom, String cheminImage, int indice) {
            ImageIcon icon = new ImageIcon(cheminImage);
            // Petite sécurité si l'image n'est pas trouvée
            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                icon = null; 
            } else {
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
            }

            JButton btn = new JButton(nom, icon);
            
            // Style
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setPreferredSize(new Dimension(160, 180));
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setFocusable(false);
            btn.setBackground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            // ACTION AU CLIC
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lancerJeu(indice);
                }
            });

            return btn;
        }

        /**
         * Fonction qui permet de lancer le niveau séléctionné dans le menu
         * @param indice
         */
        private void lancerJeu(int indice) {
            frame.getContentPane().removeAll();
            actu = new Labyrinthe(paths[indice]);

            frame.getContentPane().add(actu);
            
            frame.revalidate();
            frame.repaint();
            
            actu.requestFocusInWindow();
            frame.pack();
            frame.setLocationRelativeTo(null);

            // LANCEMENT DU TIMER (Boucle de jeu)
            // On arrête l'ancien s'il existe
            if (timer != null && timer.isRunning()) timer.stop();
            
            timer = new Timer(50, ev -> {
                int retour = actu.tour();
                if( retour == 1 || retour == 0 ) {
                    actu.repaint();
                }
                else {
                    timer.stop();
                    this.actu = null;

                    this.frame.getContentPane().removeAll();
                    this.removeAll();
                    reconstruireInterface();

                    frame.getContentPane().add(this);
                    frame.revalidate();
                    frame.repaint();
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                }
            });
            timer.start();
        }

        /**
         * 
         * @return le labyrinthe correspondant au niveau actuel
         */
        public Labyrinthe getActu() { return this.actu; }

        /**
         * Fonction auxiliaire utilisée pour initialisé les attributs de l'interface
         * et le JPanel
         */
        private void reconstruireInterface() {
            this.setLayout(new GridBagLayout());
            this.setBackground(Color.DARK_GRAY);

            JLabel titre = new JLabel("Redux");
            titre.setFont(new Font("Arial", Font.BOLD, 40));
            titre.setForeground(Color.WHITE);

            JPanel panelBoutons = new JPanel(new GridLayout(1, 3, 20, 0));
            panelBoutons.setOpaque(false);

            // Ajout des boutons
            panelBoutons.add(creerBouton("Niveau 1", "src/img/lvl1.png", 0));
            panelBoutons.add(creerBouton("Niveau 2", "src/img/lvl2.png", 1));
            panelBoutons.add(creerBouton("Niveau 3", "src/img/lvl3.png", 2));

            // Placement du Titre
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0; gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 50, 0);
            this.add(titre, gbc);

            // Placement des Boutons
            gbc.gridy = 1;
            this.add(panelBoutons, gbc);
        }
    }