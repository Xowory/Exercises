package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML public Button dodaj,zakoncz;
    @FXML public TextArea textArea;
    @FXML
    protected void addAlarm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/add.fxml"));
        Parent parent = fxmlLoader.load();

        Add_controller con =  fxmlLoader.getController();
        con.dod = textArea;

        Scene scene2 = new Scene(parent,400,475);
        Stage window = new Stage();
        window.setTitle("Dodaj alarm");
        window.setScene(scene2);
        window.show();
    }
    @FXML
    protected void close() throws IOException {
        Stage stage = (Stage) zakoncz.getScene().getWindow();
        stage.close();
    }
}
