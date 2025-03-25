package ClientServeur;

import Documents.Document;

import java.time.LocalDateTime;
import java.util.List;

public class Abonne {

    private final int ID;
    private final String nom;
    private final LocalDateTime dateNaissance;
    private List<Document> documentsReserves;

    public Abonne(int id, String nom, LocalDateTime dateNaissance) {
        ID = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public int getID() {
        return ID;
    }

    public LocalDateTime getDateNaissance() {
        return dateNaissance;
    }

    public List<Document> getDocumentsReserves() {
        return documentsReserves;
    }

    public void setDocumentsReserves(List<Document> documentsReserves) {
        this.documentsReserves = documentsReserves;
    }
}
