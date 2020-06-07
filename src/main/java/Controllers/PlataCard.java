package Controllers;

import Exceptii.DetaliiCardIncorecte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class PlataCard {
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextField text3;
    @FXML
    private TextField text4;

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("MetodePlata.fxml"));
            AnchorPane paginaA = (AnchorPane) loader.load();
            Scene scene = new Scene(paginaA);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finalizare(ActionEvent actionEvent) {
        if(verificare() == 1) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("ComandaFinalizata.fxml"));
                AnchorPane paginaA = (AnchorPane) loader.load();
                Scene scene = new Scene(paginaA);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new DetaliiCardIncorecte();
        }
    }

    public int verificare(){
        Calendar data1 = Calendar.getInstance();
        data1.setTime(new Date());
        if(text1.getText().length() != 16 || (Integer.parseInt(text3.getText()) < data1.get(Calendar.YEAR))) return 0;
        else if((Integer.parseInt(text3.getText()) == data1.get(Calendar.YEAR))){
            if(Integer.parseInt(text2.getText()) < data1.get(Calendar.MONTH)) return 0;
        }
        else if(text1.getText() == null || text2.getText() == null || text3.getText() == null
                || text4.getText() == null) return 0;
        return 1;
    }
}
