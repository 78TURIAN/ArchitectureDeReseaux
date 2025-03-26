package Infrastructure;

import Abonnés.Abonne;
import Documents.Document;
import Exeptions.ReservationException;
import bttp.Codage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServiceReservation extends Service{

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);

            out.println(Codage.coder("Quel est votre numéro d'abonné ?"));
            String IDAbonne = Codage.decoder(in.readLine());

            out.println(Codage.coder("Quel document voulez vous reserver ?"));
            String IDDocument = Codage.decoder(in.readLine());

            Abonne ab = getMediatheque().chercherUnAbonneParID(Integer.parseInt(IDAbonne));
            Document doc = getMediatheque().chercherUnDocumentParID(Integer.parseInt(IDDocument));

            if (doc == null){throw new ReservationException("Ce document n'existe pas");}
            if(ab == null){throw new ReservationException("Cet abonne n'existe pas");}

            doc.reserver(ab);

            out.println(Codage.coder("la reservation est validee n'oubliez pas de venir chercher votre document avant une heure a partir de maintenant"));
        }catch (IOException e)
        {
            EnvoyerMsg(e.getMessage());
        }
        catch (Exception e)
        {
            EnvoyerMsg(e.getMessage());
        }finally {
            try {
                getSocket().close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void EnvoyerMsg(String msg)
    {
        try {
            PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);
            out.println(Codage.coder(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
