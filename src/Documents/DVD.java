package Documents;

import Abonnés.Abonne;
import Exeptions.EmpruntException;
import Exeptions.ReservationException;

import java.time.LocalDate;
import java.time.Period;

public class DVD extends DocumentBasique{

    private boolean adulte;

    public DVD(int numero, String titre, boolean adulte) {
        super(numero, titre);
        this.adulte = adulte;
    }

    public boolean isAdulte() {
        return adulte;
    }

    @Override
    public synchronized void emprunter(Abonne ab) throws EmpruntException
    {
        if (this.adulte && !estMajeur(ab))
        {
            throw new EmpruntException(ab.getNom() + " ne peut pas emprunter " + getTitre() + " car il est mineur.");
        }
        else
        {
            super.emprunter(ab);
        }
    }
    @Override
    public synchronized void reserver(Abonne ab) throws EmpruntException
    {
        if (this.adulte && !estMajeur(ab))
        {
            throw new ReservationException(ab.getNom() + " ne peut pas reserver " + getTitre() + " car il est mineur.");
        }
        else
        {
            super.reserver(ab);
        }
    }

    private static boolean estMajeur(Abonne abonne) {
        int ageMin = 18;
        LocalDate naissance = abonne.getDateNaissance().toLocalDate();
        LocalDate aujourdHui = LocalDate.now();

        int age = Period.between(naissance, aujourdHui).getYears();
        return age >= ageMin;
    }
}
