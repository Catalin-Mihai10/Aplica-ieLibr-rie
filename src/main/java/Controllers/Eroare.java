package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;


public class Eroare {

    @FXML
    private Text text;

    public void setText(String t){
        text.setText(t);
    }

    public void ok(ActionEvent actionEvent){
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
