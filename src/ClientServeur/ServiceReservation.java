package ClientServeur;

import Documents.Document;
import Exeptions.EmpruntException;
import Exeptions.ReservationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServiceReservation extends Service{
    private mediatheque mediatheque;

    public ServiceReservation(mediatheque mediatheque)
    {
        super();
        this.mediatheque = mediatheque;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);

            out.println("Quel est votre numéro d'abonné ?");
            String IDAbonne = in.readLine();

            out.println("Quel document voulez vous reserver ?");
            String IDDocument = in.readLine();

            Abonne ab = mediatheque.chercherUnAbonneParID(Integer.parseInt(IDAbonne));
            Document doc = mediatheque.chercherUnDocumentParID(Integer.parseInt(IDDocument));

            if (doc == null){throw new ReservationException("Ce document n'existe pas");}
            if(ab == null){throw new ReservationException("Cet abonne n'existe pas");}

            doc.reserver(ab);
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
            out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
