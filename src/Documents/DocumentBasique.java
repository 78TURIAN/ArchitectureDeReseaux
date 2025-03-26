package Documents;

import java.time.LocalDateTime;

import ClientServeur.Abonne;
import Exeptions.EmpruntException;
import Exeptions.ReservationException;
import Exeptions.RetournerExeption;

public class DocumentBasique implements Document {

    private int numero;
    private String titre;
    private EtatDocument etat;

    private Abonne reservePar;
    private LocalDateTime dateReservation;

    private LocalDateTime dateEmprunte;

    public DocumentBasique(int numero, String titre) {
        this.numero = numero;
        this.titre = titre;
        this.etat = EtatDocument.LIBRE;//le document est libre de base
    }

    @Override
    public int numero() {
        return numero;
    }

    @Override
    public synchronized void reserver(Abonne ab) throws ReservationException {
        verifierExpiration();

        if (etat == EtatDocument.LIBRE)
        {
            reservePar = ab;
            dateReservation = LocalDateTime.now();
            etat = EtatDocument.RESERVE;
        }
        else if (etat == EtatDocument.RESERVE)
        {
            throw new ReservationException("Ce document est deja reserve");
        }
        else
        {
            throw new ReservationException("Ce document est deja emprunte");
        }
    }

    @Override
    public synchronized void emprunter(Abonne ab) throws EmpruntException {
        verifierExpiration();

        if (etat == EtatDocument.LIBRE)
        {
            dateEmprunte = LocalDateTime.now();
            etat = EtatDocument.EMPRUNTE;
        }
        else if(etat == EtatDocument.RESERVE)
        {
            if(ab.equals(reservePar))
            {
                reservePar = null;
                dateReservation = null;
                dateEmprunte = LocalDateTime.now();
                etat = EtatDocument.EMPRUNTE;
            }
            else
            {
                throw new EmpruntException("Ce document est deja reserve");
            }
        }
        else if(etat == EtatDocument.EMPRUNTE)
        {
            throw new EmpruntException("Ce document est deja emprunte");
        }
    }

    @Override
    public synchronized void retourner() {
        if(etat == EtatDocument.EMPRUNTE)
        {
            this.etat = EtatDocument.LIBRE;
            this.dateEmprunte = null;
            this.reservePar = null;
            this.dateReservation = null;
        }
        else
        {
            throw new RetournerExeption("Ce document ne peut pas etre retourne");
        }
    }

    public String getTitre() {
        return titre;
    }
    private void verifierExpiration() {
        if (etat == EtatDocument.RESERVE && dateReservation != null &&
                dateReservation.plusHours(1).isBefore(LocalDateTime.now())) {
            reservePar = null;
            dateReservation = null;
            etat = EtatDocument.LIBRE;
        }
    }

}
