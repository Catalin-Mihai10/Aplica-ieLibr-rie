package Controllers;

import Exceptii.UsernameSauParolaGresite;
import Utilizatori.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PaginaAutentificareManager {

    @FXML
    private TextField username;
    @FXML
    private PasswordField parola;

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("PaginaAutentificare.fxml"));
            AnchorPane paginaA = (AnchorPane) loader.load();
            Scene scene = new Scene(paginaA);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectionare(ActionEvent actionEvent)throws UsernameSauParolaGresite {
        try {
            if(verificareManager()==1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("PaginaManager.fxml"));
                AnchorPane paginaA = (AnchorPane) loader.load();
                Scene scene = new Scene(paginaA);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            else {
                username.clear();
                parola.clear();
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getClassLoader().getResource("Eroare.fxml"));
                    AnchorPane paginaA = (AnchorPane) loader.load();
                    Eroare controller = loader.getController();
                    controller.setText("Username sau parola\ngresite!");
                    Scene scene = new Scene(paginaA);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                throw new UsernameSauParolaGresite();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int verificareManager() {
        JSONParser parser = new JSONParser();
        Manager m = new Manager(username.getText(),parola.getText());
        try (Reader reader = new FileReader("src/main/resources/manager.json")) {

            JSONObject obiect = (JSONObject) parser.parse(reader);
            if (obiect.get("Username:").toString().equals(m.getUsername()) && obiect.get("Parola:").toString().equals(m.getEncodedPassword())) {
                return 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
