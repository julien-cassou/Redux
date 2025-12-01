package Labyrinthe;
public class CaseIntraversable extends Case {
    
    public CaseIntraversable(int l, int c) {
        super(l,c);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "#";
    }
}
