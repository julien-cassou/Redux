package Labyrinthe;

public enum Direction {
    HAUT, BAS, GAUCHE, DROITE;

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
