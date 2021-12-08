import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "marka", "model", "pojemnosc", "rokProdukcji", "przebieg"})
public class Car {
    private int id;
    private String marka;
    private String model;
    private double pojemnosc;
    private int rokProdukcji;
    private int przebieg;

    public Car() {
    }

    public Car(int id, String marka, String model, double pojemnosc, int rokProdukcji, int przebieg) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.pojemnosc = pojemnosc;
        this.rokProdukcji = rokProdukcji;
        this.przebieg = przebieg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(double pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", pojemnosc=" + pojemnosc +
                ", rokProdukcji=" + rokProdukcji +
                ", przebieg=" + przebieg +
                '}';
    }
}
