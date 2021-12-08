import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Watek extends Thread{
    private Socket socket;
    private Connection conn;
    Car car1 = new Car();
    ArrayList<Car> samochody = new ArrayList<>();
    public Watek(Socket socket, Connection conn){
        this.socket = socket;
        this.conn = conn;
    }
    @Override
    public void run(){
        try{
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            JSONObject wyjscie = new JSONObject();
            ObjectMapper mapper = new ObjectMapper();
            String s = reader.readLine();
            JSONObject json = new JSONObject(s);

            if(json.has("dodaj")){
                JSONObject json2 = new JSONObject(json.getJSONObject("dodaj").toString());
                //Car car2 = mapper.readValue((DataInput) json2,Car.class);
                String q1 = "INSERT INTO Samochody VALUES (?,?,?,?,?,?)";
                PreparedStatement pstmt1 = conn.prepareStatement(q1);
                pstmt1.setInt(1, json2.getInt("id"));
                pstmt1.setString(2, json2.getString("marka"));
                pstmt1.setString(3, json2.getString("model"));
                pstmt1.setDouble(4, json2.getDouble("pojemnosc"));
                pstmt1.setInt(5, json2.getInt("rok produkcji"));
                pstmt1.setInt(6, json2.getInt("przebieg"));
                ResultSet rset = pstmt1.executeQuery();
                wyjscie.put("dodaj","Dodano");
                writer.write(wyjscie.toString());
                pstmt1.close();
                rset.close();
            }
            else if(json.has("usun")){
                wczytaj();
                JSONObject json2 = new JSONObject(json.getJSONObject("usun").toString());
                int id = json2.getInt("id");
                String q1 = "SELECT id FROM Samochody WHERE id = ?" ;
                PreparedStatement pstmt1 = conn.prepareStatement(q1);
                pstmt1.setInt(1, id);

                String q2 = "DELETE FROM Samochody WHERE id = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(q2);

                ResultSet rset = pstmt1.executeQuery();

                if (rset.next()) {
                    pstmt2.setInt(1, id);
                    pstmt2.execute();
                    wyjscie.put("usun","Usunięto");
                    writer.write(wyjscie.toString());
                }
                else {
                    wyjscie.put("usun","Nie istnieje samochód o podanym id.");
                    writer.write(wyjscie.toString());
                }
                rset.close();
                pstmt1.close();
                pstmt2.close();
            }
            else if(json.has("wyswietl")){
                wczytaj();
                JSONArray tab = new JSONArray(samochody);
                wyjscie.put("wyswietl",tab);
                writer.write(wyjscie.toString());
            }
            else if(json.has("szukaj")){
                wczytaj();
                JSONObject json2 = new JSONObject(json.getJSONObject("szukaj").toString());
                int rokProdukcji = json2.getInt("rok produkcji");
                int przebieg = json2.getInt("przebieg");
                double pojemnosc = json2.getDouble("pojemnosc");
                ArrayList<Car> lista = samochody.stream()
                        .filter(car -> car.getRokProdukcji() > rokProdukcji)
                        .filter(car -> car.getPrzebieg() < przebieg)
                        .filter(car -> car.getPojemnosc() > pojemnosc)
                        .collect(Collectors.toCollection(ArrayList::new));
                JSONArray tablica = new JSONArray(lista);
                wyjscie.put("szukaj",tablica);
                writer.write(wyjscie.toString());
            }
            writer.newLine();
            writer.flush();

        }
        catch(IOException | SQLException e){
            e.printStackTrace();
        }
    }
    public void wczytaj() throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Samochody");
        while (rs.next()) {
            int id = rs.getInt(1);
            String marka = rs.getString(2);
            String model = rs.getString(3);
            double pojemnosc = rs.getDouble(4);
            int rok = rs.getInt(5);
            int przebieg = rs.getInt(6);
            samochody.add(new Car(id,marka,model,pojemnosc,rok,przebieg));
        }
    }

}
