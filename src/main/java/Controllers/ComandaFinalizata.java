package Controllers;

import javafx.event.ActionEvent;

public class ComandaFinalizata extends ControllerGeneral{

    public void exit(ActionEvent actionEvent) {
        String s = "PaginaClient.fxml";
        redirectioneazaPagina(actionEvent,s);
    }
}
