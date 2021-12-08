package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Add_controller {
    private int minus = 23,plus = 1, minus2 = 59,plus2 = 1, g = 0, m = 0;
    @FXML private Button zapisz,anuluj;
    @FXML private CheckBox rpt;
    @FXML private ToggleButton pn;
    @FXML private ToggleButton wt;
    @FXML private ToggleButton sr;
    @FXML private ToggleButton czw;
    @FXML private ToggleButton pt;
    @FXML private ToggleButton sb;
    @FXML private ToggleButton nd;
    @FXML private Slider slider;
    @FXML private  TextField textField;
    @FXML private Label godziny;
    @FXML private Label minuty;
    @FXML private Label g_m;
    @FXML private Label g_p;
    @FXML private Label m_m;
    @FXML private Label m_p;
    public TextArea dod;
    @FXML
    protected void save() throws IOException {
        dod.appendText("[");
        if(pn.isSelected())
            dod.appendText("Pn, ");
        if(wt.isSelected())
            dod.appendText("Wt, ");
        if(sr.isSelected())
            dod.appendText("Sr, ");
        if(czw.isSelected())
            dod.appendText("Czw, ");
        if(pt.isSelected())
            dod.appendText("Pt, ");
        if(sb.isSelected())
            dod.appendText("Sb, ");
        if(nd.isSelected())
            dod.appendText("Nd, ");
        if(rpt.isSelected())
            dod.appendText("Powtarzaj");

        dod.appendText("] " + godziny.getText() + ":" + minuty.getText() + ", Głośność: " + (int)slider.getValue() + "%, " +textField.getText() + "\n");

        Stage stage = (Stage) zapisz.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void g_minus() throws IOException{
        if (minus < 10)
            godziny.setText("0"+Integer.toString(minus));
        else
            godziny.setText(Integer.toString(minus));
        minus--;
        if(minus < 0) minus = 23;
        if(minus > 23) minus = 0;
        if (minus < 10)
            g_m.setText("0"+Integer.toString(minus));
        else
            g_m.setText(Integer.toString(minus));
        plus--;
        if(plus > 23) plus = 0;
        if(plus < 0) plus = 23;
        if(plus < 10)
            g_p.setText("0"+Integer.toString(plus));
        else
            g_p.setText(Integer.toString(plus));
    }
    @FXML
    protected void g_plus() throws IOException{
        if (plus < 10)
            godziny.setText("0"+Integer.toString(plus));
        else
            godziny.setText(Integer.toString(plus));
        minus++;
        if(minus > 23) minus = 0;
        if(minus < 0) minus = 23;
        if (minus < 10)
            g_m.setText("0"+Integer.toString(minus));
        else
            g_m.setText(Integer.toString(minus));
        plus++;
        if(plus > 23) plus = 0;
        if(plus < 0) plus = 23;
        if(plus < 10)
            g_p.setText("0"+Integer.toString(plus));
        else
            g_p.setText(Integer.toString(plus));
    }
    @FXML
    protected void m_minus() throws IOException{
        if (minus2 < 10)
            minuty.setText("0"+Integer.toString(minus2));
        else
            minuty.setText(Integer.toString(minus2));
        minus2--;
        if(minus2 < 0) minus2 = 59;
        if(minus2 > 59) minus2 = 0;
        if (minus2 < 10)
            m_m.setText("0"+Integer.toString(minus2));
        else
            m_m.setText(Integer.toString(minus2));
        plus2--;
        if(plus2 > 59) plus2 = 0;
        if(plus2 < 0) plus2 = 59;
        if(plus2 < 10)
            m_p.setText("0"+Integer.toString(plus2));
        else
            m_p.setText(Integer.toString(plus2));
    }
    @FXML
    protected void m_plus() throws IOException{
        if (plus2 < 10)
            minuty.setText("0"+Integer.toString(plus2));
        else
            minuty.setText(Integer.toString(plus2));
        minus2++;
        if(minus2 < 0) minus2 = 59;
        if(minus2 > 59) minus2 = 0;
        if (minus2 < 10)
            m_m.setText("0"+Integer.toString(minus2));
        else
            m_m.setText(Integer.toString(minus2));
        plus2++;
        if(plus2 > 59) plus2 = 0;
        if(plus2 < 0) plus2 = 59;
        if(plus2 < 10)
            m_p.setText("0"+Integer.toString(plus2));
        else
            m_p.setText(Integer.toString(plus2));
    }
    @FXML
    protected void close() throws IOException {

        Stage stage = (Stage) anuluj.getScene().getWindow();
        stage.close();
    }
}
