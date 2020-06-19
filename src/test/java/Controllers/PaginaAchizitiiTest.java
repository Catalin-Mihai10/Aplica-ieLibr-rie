package Controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaginaAchizitiiTest extends ApplicationTest {

    private PaginaAchizitii a;

    @Before
    public void setUp() throws Exception {
        a = new PaginaAchizitii();
        a.listaAchizitii = new ListView<>();
        a.text = new TextArea();
        PaginaLogIn.nume = "Ion";
        a.observ = FXCollections.observableArrayList();
    }

    @Test
    public void setlistaAchizitii() {
        a.setlistaAchizitii();
        assertNotNull(a.listaAchizitii);
        assertNotNull(a.observ);
        assertEquals(3,a.observ.size());
    }

}