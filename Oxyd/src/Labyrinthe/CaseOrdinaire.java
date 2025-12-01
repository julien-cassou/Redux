package Labyrinthe;

public class CaseOrdinaire extends Case {
    private Entite contenant;

    public CaseOrdinaire(int l, int c) {
        super(l, c);
        this.contenant = null;
    }

    public CaseOrdinaire(int l, int c, Entite e) {
        super(l, c);
        this.contenant = e;
    }

    @Override
    public boolean isEmpty() {
        return this.contenant == null;
    }

    public Entite getEntite() { return this.contenant;}
}
