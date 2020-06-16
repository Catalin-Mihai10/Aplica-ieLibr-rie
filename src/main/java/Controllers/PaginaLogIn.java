package Controllers;

import Exceptii.UsernameSauParolaGresite;
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
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class PaginaLogIn extends ControllerGeneral {

    @FXML
    private TextField username;
    @FXML
    private PasswordField parola;
    protected static String nume;

    public void back(ActionEvent actionEvent) {
        String s = "PaginaAutentificare.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public void redirectionare(ActionEvent actionEvent) throws UsernameSauParolaGresite {
        nume = username.getText();
        if(verificareUtilizator()==1) {
            String s = "PaginaClient.fxml";
            redirectioneazaPagina(actionEvent,s);
        }else {
                username.clear();
                parola.clear();
                String m = "Username sau parola\ngresite!";
                redirectionareEroare(m);
                throw new UsernameSauParolaGresite();
            }
    }

    public int  verificareUtilizator() {
        JSONParser parser = new JSONParser();
        Client c = new Client(username.getText(),parola.getText());
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\user.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();

                if(obiect.get("Username:").toString().equals(c.getUsername()) &&
                        obiect.get("Parola:").equals(c.getEncodePassword())) return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getNume(){
        return nume;
    }
}
