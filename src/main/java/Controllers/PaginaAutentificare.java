package Controllers;

import javafx.event.ActionEvent;

public class PaginaAutentificare extends ControllerGeneral{

    public void handle(ActionEvent actionEvent) {
        String s = "PaginaLogIn.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public void handle1(ActionEvent actionEvent) {
        String s = "PaginaInregistrare.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public void handle2(ActionEvent actionEvent) {
        String s = "PaginaAutentificareManager.fxml";
        redirectioneazaPagina(actionEvent,s);
    }
}
