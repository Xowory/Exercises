import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.*;
import java.net.Socket;

public class Klient {
    private Socket socket;
    JSONObject json;
    public Klient(Socket socket) {
        this.socket = socket;
    }
    public void ff() throws IOException{
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        System.out.println("1. Dodaj samochód.\n2. Usuń samochód.\n3. Wyświetl samochody.\n4. Szukaj samochodu.");
        Scanner scan = new Scanner(System.in);
        int n;
        n = scan.nextInt();
        switch (n){
            case 1:{
                json = dodaj();
                break;
            }
            case 2:{
                json = usun();
                break;
            }
            case 3:{
                json = wyswietl();
                break;
            }
            case 4:{
                json = szukaj();
                break;
            }
        }
        writer.write(json.toString());
        writer.newLine();
        writer.flush();

        String s = reader.readLine();
        JSONObject json2 = new JSONObject(s);
        if(json2.has("dodaj")){
            System.out.println(json2.get("dodaj"));
        }
        else if(json2.has("usun")){
            System.out.println(json2.get("usun"));
        }
        else if(json2.has("wyswietl")){
            JSONArray array = json2.getJSONArray("wyswietl");
            for(Object i : array){
                System.out.println(i.toString());
            }
        }
        else if(json2.has("szukaj")){
            JSONArray array = json2.getJSONArray("szukaj");
            for(Object i : array){
                System.out.println(i.toString());
            }
        }
        reader.close();
        socket.close();
    }
    private JSONObject dodaj() throws IOException{
        JSONObject tmp = new JSONObject();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj id, markę, model, pojemność, rok produkcji oraz przebieg:");
        int id = Integer.parseInt(reader.readLine());
        String marka = reader.readLine();
        String model = reader.readLine();
        double pojemnosc = Double.parseDouble(reader.readLine());
        int rokProdukcji = Integer.parseInt(reader.readLine());
        int przebieg = Integer.parseInt(reader.readLine());
        reader.close();
        tmp.put("id", id);
        tmp.put("marka",marka);
        tmp.put("model", model);
        tmp.put("pojemnosc", pojemnosc);
        tmp.put("rok produkcji", rokProdukcji);
        tmp.put("przebieg", przebieg);
        JSONObject tmp2 = new JSONObject();
        tmp2.put("dodaj",tmp);
        return tmp2;
    }
    private JSONObject usun() throws IOException{
        JSONObject tmp = new JSONObject();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj id:");
        int id1 = Integer.parseInt(reader.readLine());
        reader.close();
        tmp.put("id", id1);
        JSONObject tmp2 = new JSONObject();
        tmp2.put("usun",tmp);
        return tmp2;
    }
    private JSONObject wyswietl() throws IOException{
        JSONObject tmp4 = new JSONObject();
        tmp4.put("wyswietl",1);
        return tmp4;
    }
    private JSONObject szukaj() throws IOException{
        JSONObject tmp = new JSONObject();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj rok produkcji, przebieg, oraz pojemnosc silnika.");
        int rokProdukcji = Integer.parseInt(reader.readLine());
        int przebieg = Integer.parseInt(reader.readLine());
        double pojemnosc = Double.parseDouble(reader.readLine());
        reader.close();
        tmp.put("rok produkcji", rokProdukcji);
        tmp.put("przebieg", przebieg);
        tmp.put("pojemnosc", pojemnosc);
        JSONObject tmp2 = new JSONObject();
        tmp2.put("szukaj",tmp);
        return tmp2;
    }
}
