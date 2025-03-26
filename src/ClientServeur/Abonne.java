package ClientServeur;

import Documents.Document;
import Exeptions.EmpruntException;
import Exeptions.ReservationException;
import Exeptions.RetournerExeption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Abonne implements Runnable {

    private static final int PORT_RESERVATION = 2000;
    private static final int PORT_EMPRUNT = 3000;
    private static final int PORT_RETOUR = 4000;

    private final int ID;
    private final String nom;
    private final LocalDateTime dateNaissance;

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

    @Override
    public void run() {
        Random r = new Random();
        while (true)
        {
            int choixAction = r.nextInt(4);
            try {
                switch (choixAction) {
                    case 0:
                        reservation(r);
                        break;
                    case 1:
                        emprun(r);
                        break;
                    case 2:
                        retour(r);
                        break;
                    default:
                        //rien
                        wait(1000);
                        break;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void reservation(Random r) throws IOException
    {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            socket = new Socket("localhost", PORT_RESERVATION);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String messageRecu = Codage.decoder(in.readLine());
            if(!messageRecu.equals("Quel est votre numéro d'abonné ?"))
            {
                throw new ReservationException("message inconnu pour la reservation a la Q1 de l'Abonne " + ID);
            }
            out.println(Codage.coder(String.valueOf(ID)));

            messageRecu = Codage.decoder(in.readLine());
            if(!messageRecu.equals("Quel document voulez vous reserver ?"))
            {
                throw new ReservationException("message inconnu pour la reservation a la Q2 de l'Abonne " + ID);
            }

            out.println(Codage.coder(String.valueOf(r.nextInt(10))));//volontairement a 10 pour qu'il puisse essayer de chopper un element qui n'existe pas

            messageRecu = Codage.decoder(in.readLine());
            System.out.println(messageRecu);

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void emprun(Random r)throws IOException
    {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            socket = new Socket("localhost", PORT_EMPRUNT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String messageRecu = Codage.decoder(in.readLine());
            if(!messageRecu.equals("Quel est votre numéro d'abonné ?"))
            {
                throw new EmpruntException("message inconnu pour l'emprunt a la Q1 de l'Abonne " + ID);
            }
            out.println(Codage.coder(String.valueOf(ID)));

            messageRecu = Codage.decoder(in.readLine());
            if(!messageRecu.equals("Quel document voulez vous emprunter ?"))
            {
                throw new EmpruntException("message inconnu pour l'emprunt a la Q2 de l'Abonne " + ID);
            }
            out.println(Codage.coder(String.valueOf(r.nextInt(10))));//volontairement a 10 pour qu'il puisse essayer de chopper un element qui n'existe pas

            messageRecu = Codage.decoder(in.readLine());
            System.out.println(messageRecu);

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void retour(Random r)throws IOException
    {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try
        {
            socket = new Socket("localhost", PORT_RETOUR);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String messageRecu = Codage.decoder(in.readLine());
            if(!messageRecu.equals("Quel est le document a retourner ?"))
            {
                throw new RetournerExeption("message inconnu pour l'emprunt a la Q2 de l'Abonne " + ID);
            }
            out.println(Codage.coder(String.valueOf(r.nextInt(10))));//volontairement a 10 pour qu'il puisse essayer de chopper un element qui n'existe pas

            messageRecu = Codage.decoder(in.readLine());
            System.out.println(messageRecu);

        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
