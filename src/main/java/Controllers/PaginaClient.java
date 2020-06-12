package Controllers;

import Exceptii.NuSaAdaugatCarte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class PaginaClient extends CosDeCumparaturi{

    @FXML
    private ListView<String> lista = new ListView<String>();
    @FXML
    private TextArea text;
    @FXML
    private TextField baraCautare;
    private JSONArray listaJson = new JSONArray();

    private ObservableList<String> observ = FXCollections.observableArrayList();
    private static ObservableList<String> observCos = FXCollections.observableArrayList();

    public void afisareSF(ActionEvent actionEvent){
        text.clear();
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
        text.clear();
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
        text.clear();
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
        text.clear();
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

    public void afisarePopulare(ActionEvent actionEvent) {
        text.clear();
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

    public void afisareDetalii(){
        String det = "";
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Titlu:").toString().equals(lista.getSelectionModel().getSelectedItem())){
                    det = "Titlu: "+obiect.get("Titlu:")+"\nAutor: "+obiect.get("Autor:")+"\nEditura: "
                            +obiect.get("Editura:")+"\nPret: "+obiect.get("Pret:")+" lei";
                }

            }
            text.setText(det);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void baraCautare(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ENTER) {
            observ.clear();
            text.clear();
            lista.getItems().clear();
            JSONParser parser = new JSONParser();
            try (Reader reader = new FileReader("src/main/resources/Carti.json")) {

                JSONArray temp = (JSONArray) parser.parse(reader);
                Iterator<JSONObject> it = temp.iterator();
                while (it.hasNext()) {
                    JSONObject obiect = it.next();
                    if(obiect.get("Titlu:").toString().equals(baraCautare.getText()) || obiect.get("Autor:").toString().equals(baraCautare.getText())
                    || obiect.get("Categorie:").toString().equals(baraCautare.getText()) || obiect.get("Editura:").toString().equals(baraCautare.getText())){
                        observ.add(obiect.get("Titlu:").toString());
                    }
                }
                lista.setItems(observ);
                baraCautare.clear();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void CitesteFisier(){
        observCos.clear();
        listaJson.clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Cos.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                listaJson.add(obj);
                if(obj.get("Username:").equals(PaginaLogIn.getNume())) {
                    observCos.add(obj.get("Titlu:").toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void adaugareInCos(){
        CitesteFisier();

        JSONObject obiect = new JSONObject();
        obiect.put("Username:", PaginaLogIn.getNume());
        obiect.put("Titlu:", lista.getSelectionModel().getSelectedItem());

        try (FileWriter fisier = new FileWriter("src/main/resources/Cos.json")) {
            listaJson.add(obiect);
            fisier.write(listaJson.toJSONString());
            fisier.flush();
        } catch (IOException e) {
            throw new NuSaAdaugatCarte();
        }
    }

    public void vizualizareCos(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("CosDeCumparaturi.fxml"));
            AnchorPane paginaA = (AnchorPane) loader.load();
            CosDeCumparaturi controller = loader.getController();
            CitesteFisier();
            controller.setListaCumparaturi(observCos);
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

    public void vizualizareAchizitii(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("PaginaAchizitii.fxml"));
            AnchorPane paginaA = (AnchorPane) loader.load();
            PaginaAchizitii controller = loader.getController();
            controller.setlistaAchizitii();
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

}


