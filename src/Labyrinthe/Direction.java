package Labyrinthe;

public enum Direction {
    HAUT, BAS, GAUCHE, DROITE;

    /**
     * Constructeur de l'enum Direction, utilisé pour 
     * les cases Tapis (Boost)
     * @param s
     * @return
     */
    public static Direction ofChar(char s) {
        switch(s) {
            case '^': return HAUT;
            case 'v': return BAS;
            case '>' : return DROITE;
            case '<' : return GAUCHE;
            default: return HAUT;
        }
    }
}
