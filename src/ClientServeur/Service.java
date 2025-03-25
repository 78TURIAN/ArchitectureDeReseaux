package ClientServeur;

import java.net.Socket;

public abstract class Service implements Runnable {
    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}
