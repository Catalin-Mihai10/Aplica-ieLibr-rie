package Controllers;

import Exceptii.UsernameSauParolaGresite;
import Utilizatori.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PaginaAutentificareManager extends ControllerGeneral{

    @FXML
    private TextField username;
    @FXML
    private PasswordField parola;

    public void back(ActionEvent actionEvent) {
        String s = "PaginaAutentificare.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public void redirectionare(ActionEvent actionEvent)throws UsernameSauParolaGresite {
        if(verificareManager()==1) {
            String s = "PaginaManager.fxml";
            redirectioneazaPagina(actionEvent,s);
        }else {
                    username.clear();
                    parola.clear();
                    String m = "Username sau parola\ngresite!";
                    redirectionareEroare(m);
                    throw new UsernameSauParolaGresite();
            }
    }

    public int verificareManager() {
        JSONParser parser = new JSONParser();
        Manager m = new Manager(username.getText(),parola.getText());
        try (Reader reader = new FileReader(getUserPath("manager.json"))) {

            JSONObject obiect = (JSONObject) parser.parse(reader);
            if (obiect.get("Username:").toString().equals(m.getUsername()) && obiect.get("Parola:").toString().equals(m.getEncodedPassword())) {
                return 1;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
