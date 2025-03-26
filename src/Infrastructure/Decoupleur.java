package Infrastructure;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class Decoupleur implements Runnable {
    private final Class<? extends Service> serviceClass;
    private final int port;
    private final Mediatheque mediatheque;

    public Decoupleur(Class<? extends Service> serviceClass, int port, Mediatheque mediatheque) {
        this.serviceClass = serviceClass;
        this.port = port;
        this.mediatheque = mediatheque;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Serveur " + serviceClass.getSimpleName() + " prêt sur le port " + port);

            while (true)
            {
                Socket clientSocket = serverSocket.accept();
                Service service = serviceClass.getDeclaredConstructor().newInstance();
                service.setSocket(clientSocket);
                service.setMediatheque(mediatheque);

                new Thread(service).start();
            }

        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
