import Exceptii.NuSaScrisUtil;
import Exceptii.UtilizatorulExistaDeja;
import Utilizatori.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PaginaInregistrare {

    @FXML
    private Text registrationMessage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField parola;

    private File fis = new File("src/main/resources/user.json");
    private static ArrayList<Client> clienti;
    private JSONArray lista = new JSONArray();
    private Client c;

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

    public static void adaugaUtilizator(Client c) throws UtilizatorulExistaDeja {
        NuExistaUtilizator(c.getUsername());
        clienti.add(c);
    }

    private static void NuExistaUtilizator(String username) throws UtilizatorulExistaDeja{
        for (Client client : clienti) {
            if (Objects.equals(username, client.getUsername()))
                throw new UtilizatorulExistaDeja(username);
        }
    }

    //Suprascrie date de fiecare data cand reporim si inregistram
    public void Inregistrare(ActionEvent actionEvent) throws UtilizatorulExistaDeja{
        c = new Client(username.getText(),parola.getText());
        clienti.add(c);
        //adaugaUtilizator(c);

        JSONObject obiect = new JSONObject();
        obiect.put("Username:",c.getUsername());
        obiect.put("Parola:",c.getPassword());

        try (FileWriter fisier = new FileWriter(fis,true)){
            lista.add(obiect);
            fisier.write(obiect.toJSONString());
            fisier.flush();
        } catch (IOException e) {
            throw new NuSaScrisUtil();
        }
    }
}
