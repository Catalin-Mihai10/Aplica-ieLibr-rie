package Controllers;

import Exceptii.UsernameSauParolaGresite;
import Utilizatori.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class PaginaLogIn extends PaginaInregistrare {

    @FXML
    private TextField username;
    @FXML
    private PasswordField parola;
    protected static String nume;

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("PaginaAutentificare.fxml"));
            AnchorPane paginaA = (AnchorPane) loader.load();
            Scene scene = new Scene(paginaA);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            // Hide this current window (if this is what you want
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectionare(ActionEvent actionEvent) throws UsernameSauParolaGresite {
        try {
            if(verificareUtilizator()==1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("PaginaClient.fxml"));
                AnchorPane paginaA = (AnchorPane) loader.load();
                Scene scene = new Scene(paginaA);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                nume=username.getText();
            }
            else {
                throw new UsernameSauParolaGresite();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int  verificareUtilizator() {
        JSONParser parser = new JSONParser();
        Client c = new Client(username.getText(),parola.getText());
        try (Reader reader = new FileReader("src/main/resources/user.json")) {

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
