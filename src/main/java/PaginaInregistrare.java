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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class PaginaInregistrare {

    @FXML
    private Text registrationMessage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField parola;

    private File fis = new File("src/main/resources/user.json");
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


    private static int ExistaUtilizator(String username) throws UtilizatorulExistaDeja{
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/user.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                if(obj.get("Username:").equals(username)) return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void CitesteFisier(){
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("src/main/resources/user.json")) {

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

    //Suprascrie date de fiecare data cand reporim si inregistram
    public void Inregistrare(ActionEvent actionEvent) throws UtilizatorulExistaDeja{
        c = new Client(username.getText(),parola.getText());
        CitesteFisier();

        if(ExistaUtilizator(c.getUsername()) == 1) {
            throw new UtilizatorulExistaDeja("Utilizator deja exista!");
        }else {
            JSONObject obiect = new JSONObject();
            obiect.put("Username:", c.getUsername());
            obiect.put("Parola:", c.getPassword());

            try (FileWriter fisier = new FileWriter(fis)) {
                lista.add(obiect);
                fisier.write(lista.toJSONString());
                fisier.flush();
                registrationMessage.setText("Contul a fost creat cu succes!");
            } catch (IOException e) {
                throw new NuSaScrisUtil();
            }
        }
    }

}
