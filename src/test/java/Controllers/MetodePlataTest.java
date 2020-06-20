package Controllers;

import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MetodePlataTest extends ApplicationTest{

    private MetodePlata m;

    @Before
    public void setUp() throws Exception {
       m = new MetodePlata();
       m.text1 = new TextField();
       m.text2 = new TextField();
       m.listaJson = new JSONArray();
       PaginaLogIn.nume = "Ion";
       m.listaAchizitii = new JSONArray();
       m.aux = new JSONArray();
    }

    @Test
    public void getFromCos() {
        m.getFromCos();
        assertNotNull(m.listaJson);
        assertEquals(0,m.listaJson.size());
    }

    @Test
    public void citeste() {
        m.Citeste();
        assertNotNull(m.listaAchizitii);
        assertEquals(9,m.listaAchizitii.size());
    }

    @Test
    public void rangSiStoc() throws IOException {
        m.rangSiStoc();
        assertNotNull(m.aux);
    }

    @Test
    public void adaugaAchizitii() throws IOException {
        m.adaugaAchizitii();
        assertNotNull(m.listaAchizitii);
    }

    @Test
    public void stergereCos() throws IOException {
        m.stergereCos();
        assertNotNull(m.listaJson);
        assertEquals(1,m.listaJson.size());
    }
}