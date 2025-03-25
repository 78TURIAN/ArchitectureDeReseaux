package ClientServeur;

import Documents.Document;
import Exeptions.EmpruntException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServiceRetour extends Service{
    private mediatheque mediatheque;

    public ServiceRetour(mediatheque mediatheque) {
        super();
        this.mediatheque = mediatheque;
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
            PrintWriter out = new PrintWriter(getSocket().getOutputStream(), true);

            out.println("Quel est le document a retourner ?");
            String IDDocument = in.readLine();

            Document doc = mediatheque.chercherUnDocumentParID(Integer.parseInt(IDDocument));
            if (doc == null){throw new EmpruntException("Ce document n'existe pas");}

            doc.retourner();

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
