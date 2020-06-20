package Controllers;

import Exceptii.NuSaAdaugatCarte;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class CosDeCumparaturi extends ControllerGeneral{

    @FXML
    private ListView<String> listaCumparaturi = new ListView<>();
    @FXML
    private TextArea text;
    private JSONArray listaJson = new JSONArray();

    public void back(ActionEvent actionEvent) {
        String s = "PaginaClient.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public void confirma(ActionEvent actionEvent) {
        if(!listaCumparaturi.getItems().isEmpty()) {
            String s = "MetodePlata.fxml";
            redirectioneazaPagina(actionEvent,s);
        }else{
            String m = "Cosul este gol!";
            redirectionareEroare(m);
        }
    }

    public void setListaCumparaturi(ObservableList<String> observCos){
        listaCumparaturi.getItems().clear();
        listaCumparaturi.setItems(observCos);
    }

    public void stergeCarte() throws IOException {
        listaJson.clear();
        text.clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath("Cos.json"))) {
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
            try (FileWriter fisier = new FileWriter(getUserPath("Cos.json"))) {
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
        String fisier = "Cos.json";
        copiaza(fisier);
    }

    public void afisarePret(){
        String det = "";
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath("Carti.json"))) {

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
