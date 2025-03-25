package Documents;

public class Livre extends DocumentBasique{

    private int nbPages;

    public Livre(int numero, String titre, int nbPages) {
        super(numero, titre);
        this.nbPages = nbPages;
    }

    public int getNbPages() {
        return nbPages;
    }
}
