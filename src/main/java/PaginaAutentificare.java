import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PaginaAutentificare {

    public void handle(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PaginaLogIn.fxml"));
            AnchorPane paginaL = (AnchorPane) loader.load();
            Scene scene = new Scene(paginaL);
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

    public void handle1(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PaginaInregistrare.fxml"));
            AnchorPane paginaI = (AnchorPane) loader.load();
            Scene scene = new Scene(paginaI);
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
}
