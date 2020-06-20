package Controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaginaClientTest extends ApplicationTest {

    private PaginaClient c;
    private PaginaLogIn p;

    @Before
    public void setUp() throws Exception {
        c = new PaginaClient();
        p = new PaginaLogIn();
        c.text = new TextArea();
        p.nume = "Ion";
        c.observCos = FXCollections.observableArrayList();
        c.observ = FXCollections.observableArrayList();
        c.lista = new ListView<>();
    }

    @Test
    public void afisareSFTest(){
        c.afisareSF();
        assertNotNull(c.observ);
        assertNotNull(c.lista);
        assertEquals(5,c.observ.size());
    }

    @Test
    public void afisareRomanceTest(){
        c.afisareRomance();
        assertNotNull(c.observ);
        assertNotNull(c.lista);
        assertEquals(5,c.observ.size());
    }

    @Test
    public void afisareAventuraTest(){
        c.afisareAventura();
        assertNotNull(c.observ);
        assertNotNull(c.lista);
        assertEquals(4,c.observ.size());
    }

    @Test
    public void afisareHorrorTest(){
        c.afisareHorror();
        assertNotNull(c.observ);
        assertNotNull(c.lista);
        assertEquals(5,c.observ.size());
    }

    @Test
    public void citesteFisierTest() {
        c.CitesteFisier();
        assertEquals(0,c.observCos.size());
    }

    @Test
    public void afisarePopulareTest(){
        c.afisarePopulare();
        assertEquals(19,c.observ.size());
    }

}