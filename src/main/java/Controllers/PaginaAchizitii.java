package Controllers;

import javafx.collections.FXCollections;
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
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class PaginaAchizitii extends ControllerGeneral{

    @FXML
    ListView<String> listaAchizitii = new ListView<>();
    @FXML
    TextArea text;
    ObservableList<String> observ = FXCollections.observableArrayList();

    public void setlistaAchizitii(){
        observ.clear();
        listaAchizitii.getItems().clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/Achizitii.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                if(obj.get("Username:").equals(PaginaLogIn.getNume())) {
                    observ.add(obj.get("Titlu:").toString());
                }
            }
            listaAchizitii.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {
        String s = "PaginaClient.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public void afisareDetalii(){
        String det = "";
        JSONParser parser = new JSONParser();
        Calendar data1 = Calendar.getInstance();
        data1.setTime(new Date());
        String[] zile;
        try (Reader reader = new FileReader("src/main/resources/Achizitii.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Titlu:").toString().equals(listaAchizitii.getSelectionModel().getSelectedItem())&&
                    obiect.get("Username:").toString().equals(PaginaLogIn.getNume())) {
                    det = "Data comenzii: " + obiect.get("Data:") + "\nComanda va ajunge in aproximativ: " + obiect.get("Estimare:") + " zile" + "\n";
                    zile = obiect.get("Data:").toString().split("-");
                    if ((data1.get(Calendar.DATE) + data1.get(Calendar.MONTH) * 30 + data1.get(Calendar.YEAR) * 365) -
                            (Integer.parseInt(zile[0]) + Integer.parseInt(zile[1]) * 30 + Integer.parseInt(zile[2]) * 365) >
                            Integer.parseInt(obiect.get("Estimare:").toString())) det += "Status livrare: Livrat";
                    else det += "Status livare: In curs de livrare";
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
