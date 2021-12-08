import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import oracle.jdbc.OracleType;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        try{
            Class.forName("org.postgresql.Driver");
            server = new ServerSocket(8080);
            while(true){
                Socket socket = server.accept();
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "123");
                System.out.println("Nowe połączenie " + socket.getLocalAddress().getHostAddress());
                Watek w = new Watek(socket, conn);
                w.start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.err.println("Blad Oracle: "+ e.getErrorCode() + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }
}
