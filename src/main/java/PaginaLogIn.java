import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaLogIn extends PaginaInregistrare{

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
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            // Hide this current window (if this is what you want
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectionare(ActionEvent actionEvent) {
        try {
            if(verificareUtilizator()==1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("PaginaClient.fxml"));
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

    public int  verificareUtilizator() {
        return 0;
    }
}
