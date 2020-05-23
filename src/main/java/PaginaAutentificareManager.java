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
            loader.setLocation(Main.class.getResource("PaginaAutentificare.fxml"));
            AnchorPane paginaA = (AnchorPane) loader.load();
            Scene scene = new Scene(paginaA);
            Stage stage = new Stage();
            //stage.setTitle("My New Stage Title");
            stage.setScene(scene);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            // Hide this current window (if this is what you want
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectionare(ActionEvent actionEvent) {
        try {
            if(verificareManager()==1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("PaginaManager.fxml"));
                AnchorPane paginaA = (AnchorPane) loader.load();
                Scene scene = new Scene(paginaA);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int verificareManager() {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/manager.json")) {

            JSONObject obiect = (JSONObject) parser.parse(reader);
            if (obiect.get("Username:").equals(username.getText()) && obiect.get("Parola:").equals(parola.getText())) {
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
