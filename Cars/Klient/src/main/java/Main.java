import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        Klient klient = new Klient(socket);
        klient.ff();
    }
}
