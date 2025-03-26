package Infrastructure;

import java.net.Socket;

public abstract class Service implements Runnable {
    private Socket socket;
    private Mediatheque mediatheque;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setMediatheque(Mediatheque mediatheque) {
        this.mediatheque = mediatheque;
    }

    public Mediatheque getMediatheque() {
        return mediatheque;
    }
}
