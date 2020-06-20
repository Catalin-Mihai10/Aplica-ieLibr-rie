package Controllers;

import Exceptii.NuSaScrisUtil;
import Exceptii.UtilizatorulExistaDeja;
import Utilizatori.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class PaginaInregistrare extends ControllerGeneral{

    @FXML
     private TextField username;
    @FXML
    private PasswordField parola;

    private JSONArray lista = new JSONArray();


    public void back(ActionEvent actionEvent) {
        String s = "PaginaAutentificare.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public int ExistaUtilizator(String username) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(getUserPath("user.json"))) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                if(obj.get("Username:").toString().equals(username)) return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void CitesteFisier(){
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(getUserPath("user.json"))) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                obj.get("Username:");
                obj.get("Parola:");
                lista.add(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void Inregistrare(ActionEvent actionEvent) throws UtilizatorulExistaDeja, IOException {
        Client c = new Client(username.getText(),parola.getText());
        if(ExistaUtilizator(c.getUsername()) == 1) {
            username.clear();
            parola.clear();
            String m = "Un utilizator cu username-ul\nacesta exista deja!";
            redirectionareEroare(m);
            throw new UtilizatorulExistaDeja(username.getText());

        }else {
            CitesteFisier();
            JSONObject obiect = new JSONObject();
            obiect.put("Username:", c.getUsername());
            obiect.put("Parola:", c.getEncodePassword());

            try (FileWriter fisier = new FileWriter(getUserPath("user.json"))) {
                lista.add(obiect);
                fisier.write(lista.toJSONString());
                fisier.flush();
            } catch (IOException e) {
                throw new NuSaScrisUtil();
            }
            String s = "PaginaAutentificare.fxml";
            redirectioneazaPagina(actionEvent,s);
            String m = "Utilizatorul a fost\ncreat cu succes!";
            redirectionareEroare(m);
        }
        String fis = "user.json";
        copiaza(fis);
    }

}
