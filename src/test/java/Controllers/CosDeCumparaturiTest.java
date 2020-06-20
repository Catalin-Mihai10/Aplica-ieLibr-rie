package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CosDeCumparaturiTest extends ApplicationTest {

    private CosDeCumparaturi c;
    private ObservableList<String> observCos = FXCollections.observableArrayList();

    @Before
    public void setUp() throws Exception {
        c = new CosDeCumparaturi();
        observCos.add("HeyHey");

        c.listaCumparaturi = new ListView<>();
        c.listaJson = new JSONArray();
        c.text = new TextArea();
    }

    @Test
    public void setListaCumparaturiTest() {
        c.setListaCumparaturi(observCos);
        assertEquals(1,c.listaCumparaturi.getItems().size());
    }


    @Test
    public void afisarePretTest(){
        c.afisarePret();
        assertNotNull(c.text);
    }
}