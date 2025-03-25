package ClientServeur;

import Documents.Document;

import java.util.List;

public class mediatheque
{
    private static final int PORT_RESERVATION = 2000;
    private static final int PORT_EMPRUNT = 3000;
    private static final int PORT_RETOUR = 4000;

    private List<Document> documents;
    private List<Abonne> abonnes;

    public mediatheque(List<Document> documents, List<Abonne> abonnes) {
        this.documents = documents;
        this.abonnes = abonnes;
    }

    public Document chercherUnDocumentParID(int ID)
    {
        for (Document d : documents)
        {
            if(d.numero() == ID)
            {
                return d;
            }
        }
        return null;
    }

    public Abonne chercherUnAbonneParID(int ID)
    {
        for(Abonne a : abonnes)
        {
            if (a.getID() == ID)
            {
                return a;
            }
        }
        return null;
    }
}
