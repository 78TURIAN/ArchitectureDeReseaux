import ClientServeur.Abonne;
import ClientServeur.Mediatheque;
import Documents.DVD;
import Documents.Document;
import Documents.Livre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        List<Document> documents = new ArrayList<>();

        documents.add(new Livre(1, "Le Petit Prince – Antoine de Saint-Exupéry", 250));
        documents.add(new Livre(2, "1984 – George Orwell", 300));
        documents.add(new Livre(3, "L'Étranger – Albert Camus", 200));
        documents.add(new Livre(4, "Harry Potter à l'école des sorciers – J.K. Rowling", 350));
        documents.add(new Livre(5, "Fahrenheit 451 – Ray Bradbury", 280));

        documents.add(new DVD(6, "Matrix", true));
        documents.add(new DVD(7, "Le Roi Lion", false));
        documents.add(new DVD(8, "Inception", true));
        documents.add(new DVD(9, "Toy Story", false));
        documents.add(new DVD(10, "Pulp Fiction", true));

        List<Abonne> abonnes = new ArrayList<>();

        abonnes.add(new Abonne(1, "Pablo", LocalDateTime.of(2005, 3, 10,0,0)));
        abonnes.add(new Abonne(2, "Emma", LocalDateTime.of(2010, 7, 22,0,0)));
        abonnes.add(new Abonne(3, "Léa", LocalDateTime.of(2000, 1, 5,0,0)));
        abonnes.add(new Abonne(4, "Tom", LocalDateTime.of(2008, 12, 1,0,0)));
        abonnes.add(new Abonne(5, "Nina", LocalDateTime.of(1998, 6, 30,0,0)));

        Mediatheque mediatheque = new Mediatheque(documents,abonnes);
        mediatheque.demarrer();
    }
}