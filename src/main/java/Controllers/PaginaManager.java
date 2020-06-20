package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class PaginaManager extends ControllerGeneral{
    @FXML
    private ListView<String> lista = new ListView<>();
    @FXML
    private TextArea text;
    @FXML
    private TextField baraCautare;

    private ObservableList<String> observ = FXCollections.observableArrayList();

    public void afisareSF(){
        text.clear();
        lista.getItems().clear();
        String s = "SF";
        citireJson(lista,s,observ);
    }

    public void afisareAventura(){
        text.clear();
        lista.getItems().clear();
        String s = "Aventura";
        citireJson(lista,s,observ);
    }

    public void afisareRomance(){
        text.clear();
        lista.getItems().clear();
        String s = "Romance";
        citireJson(lista,s,observ);
    }

    public void afisareHorror(){
        text.clear();
        lista.getItems().clear();
        String s = "Horror";
        citireJson(lista,s,observ);
    }

    public void afisareDetalii(){
        String det = "";
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Titlu:").toString().equals(lista.getSelectionModel().getSelectedItem())){
                    det = "Titlu: "+obiect.get("Titlu:")+"\nAutor: "+obiect.get("Autor:")+"\nEditura: "
                            +obiect.get("Editura:")+"\nPret: "+obiect.get("Pret:")+" lei"+"\nStoc: "
                            +obiect.get("Stoc:")+" bucati";
                }
            }
            text.setText(det);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void afisareStoc(){
        text.clear();
        lista.getItems().clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Carti.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                    observ.add(obiect.get("Titlu:").toString());
            }
            lista.setItems(observ);
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
            try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Carti.json")) {

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

    public void afisareAchizitii(){
        text.clear();
        lista.getItems().clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Achizitii.json")) {
            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            Calendar data1 = Calendar.getInstance();
            data1.setTime(new Date());
            String str = data1.get(Calendar.DATE)+"-"+data1.get(Calendar.MONTH)+"-"+data1.get(Calendar.YEAR);
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Data:").toString().equals(str)){
                    observ.add(obiect.get("Username:").toString()+": "+obiect.get("Titlu:").toString());
                }
            }
            lista.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void logOut(ActionEvent actionEvent) {
        String s = "PaginaAutentificare.fxml";
        redirectioneazaPagina(actionEvent,s);
    }
}
