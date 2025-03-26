package ClientServeur;

import Documents.Document;

import java.util.List;

public class Mediatheque
{
    private static final int PORT_RESERVATION = 2000;
    private static final int PORT_EMPRUNT = 3000;
    private static final int PORT_RETOUR = 4000;

    private List<Document> documents;
    private List<Abonne> abonnes;

    public Mediatheque(List<Document> documents, List<Abonne> abonnes) {
        assert documents != null;
        assert abonnes != null;

        this.documents = documents;
        this.abonnes = abonnes;
    }

    public void demarrer()
    {
        new Thread(new ServeurBTTP(ServiceReservation.class, PORT_RESERVATION, this)).start();
        new Thread(new ServeurBTTP(ServiceEmprun.class, PORT_EMPRUNT, this)).start();
        new Thread(new ServeurBTTP(ServiceRetour.class, PORT_RETOUR, this)).start();

        for (Abonne ab : abonnes) {
            new Thread(ab).start();
        }
    }

    public synchronized Document chercherUnDocumentParID(int ID)
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

    public synchronized Abonne chercherUnAbonneParID(int ID)
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
