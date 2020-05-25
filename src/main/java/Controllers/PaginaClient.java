package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class PaginaClient {

    @FXML
    private ListView<String> lista = new ListView<String>();
    private ObservableList<String> observ = FXCollections.observableArrayList();

    public void afisareSF(ActionEvent actionEvent){
        lista.getItems().clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Categorie:").toString().equals("SF")){
                    observ.add(obiect.get("Titlu:").toString());
                }
            }
            lista.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void afisareActiune(ActionEvent actionEvent){
        lista.getItems().clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Categorie:").toString().equals("Actiune")){
                    observ.add(obiect.get("Titlu:").toString());
                }
            }
            lista.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void afisareDrama(ActionEvent actionEvent){
        lista.getItems().clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Categorie:").toString().equals("Drama")){
                    observ.add(obiect.get("Titlu:").toString());
                }
            }
            lista.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void afisareHorror(ActionEvent actionEvent){
        lista.getItems().clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Categorie:").toString().equals("Horror")){
                    observ.add(obiect.get("Titlu:").toString());
                }
            }
            lista.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void afisarePopulare(ActionEvent actionEvent) throws JSONException{
        lista.getItems().clear();
        ArrayList<JSONObject> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                list.add(obiect);
            }
            Collections.sort(list, new Comparator<JSONObject>() {
                private static final String cheie = "Rang:";

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    Long valA;
                    Long valB = null;
                    valA = (Long) a.get(cheie);
                    valB = (Long) b.get(cheie);

                    return -valA.compareTo(valB);
                }
            });
            Iterator<JSONObject> li = list.iterator();
            while(li.hasNext()){
                JSONObject sortat = li.next();
                observ.add(sortat.get("Titlu:").toString());
            }
            lista.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


