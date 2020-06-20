package Controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaginaManagerTest extends ApplicationTest {

    private PaginaManager m;
    @Before
    public void setUp() throws Exception {

        m= new PaginaManager();
        m.lista = new ListView<>();
        m.text = new TextArea();
        m.baraCautare = new TextField();
        m.observ = FXCollections.observableArrayList();
    }

    @Test
    public void afisareSFtest() {
        m.afisareSF();
        assertNotNull(m.observ);
        assertNotNull(m.lista);
        assertEquals(5,m.observ.size());
    }

    @Test
    public void afisareActiune() {
        m.afisareActiune();
        assertNotNull(m.observ);
        assertNotNull(m.lista);
        assertEquals(4,m.observ.size());
    }

    @Test
    public void afisareDrama() {
        m.afisareDrama();
        assertNotNull(m.observ);
        assertNotNull(m.lista);
        assertEquals(5,m.observ.size());
    }

    @Test
    public void afisareHorror() {
        m.afisareHorror();
        assertNotNull(m.observ);
        assertNotNull(m.lista);
        assertEquals(5,m.observ.size());
    }

    @Test
    public void afisareDetalii() {
        m.afisareDetalii();
        assertNotNull(m.text);
    }

    @Test
    public void afisareStoc() {
        m.afisareStoc();
        assertNotNull(m.observ);
        assertNotNull(m.lista);
    }

    @Test
    public void afisareAchizitii() {
        m.afisareAchizitii();
        assertNotNull(m.observ);
        assertNotNull(m.lista);
    }



}