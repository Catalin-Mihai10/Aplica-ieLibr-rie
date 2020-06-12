package Controllers;

import Exceptii.NuSaAdaugatCarte;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class CosDeCumparaturi {

    @FXML
    private ListView<String> listaCumparaturi = new ListView<>();
    @FXML
    private TextArea text;
    private JSONArray listaJson = new JSONArray();

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("PaginaClient.fxml"));
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

    public void confirma(ActionEvent actionEvent) {
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

    public void setListaCumparaturi(ObservableList<String> observCos){
        listaCumparaturi.getItems().clear();
        listaCumparaturi.setItems(observCos);
    }

    public void stergeCarte(){
        listaJson.clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Cos.json")) {
            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                listaJson.add(obj);
            }
            JSONObject obiect = new JSONObject();
            Iterator<JSONObject> ob = listaJson.iterator();
            while(ob.hasNext()){
                obiect = ob.next();
                if(obiect.get("Username:").equals(PaginaLogIn.getNume()) &&
                        obiect.get("Titlu:").equals(listaCumparaturi.getSelectionModel().getSelectedItem())){
                    listaCumparaturi.getItems().remove(listaCumparaturi.getSelectionModel().getSelectedItem());
                    break;
                }
            }
            listaJson.remove(obiect);
            try (FileWriter fisier = new FileWriter("src/main/resources/Cos.json")) {
                fisier.write(listaJson.toJSONString());
                fisier.flush();
            } catch (IOException e) {
                throw new NuSaAdaugatCarte();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void afisareSuma(){
        String det = "";
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Titlu:").toString().equals(listaCumparaturi.getSelectionModel().getSelectedItem())){
                    det = "Pret: "+obiect.get("Pret:")+" lei";
                }
            }
            text.setText(det);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
