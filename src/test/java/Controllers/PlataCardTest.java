package Controllers;

import Exceptii.DetaliiCardIncorecte;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;

public class PlataCardTest extends ApplicationTest {

    private PlataCard m;

    @Before
    public void setUp() throws Exception {
        m = new PlataCard();
        m.text1 = new TextField();
        m.text2 = new TextField();
        m.text3 = new TextField();
        m.text4 = new TextField();
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

    @Test
    public void isNumerictest() {
        String s1 = "adsa";
        String s2 = "";
        String s3 = "24";
        assertFalse(m.isNumeric(s1));
        assertFalse(m.isNumeric(s2));
        assertTrue(m.isNumeric(s3));
    }

    @Test
    public void verificaretest1() {
        m.text1.setText("");
        m.text2.setText("");
        m.text3.setText("");
        m.text4.setText("");
        assertFalse(m.verificare());
    }

    @Test
    public void verificaretest2() {
        m.text1.setText("1234567891234567");
        m.text2.setText("1");
        m.text3.setText("2019");
        m.text4.setText("327163");
        assertFalse(m.verificare());
    }

    @Test
    public void verificaretest3() {
        m.text1.setText("1234567891234567");
        m.text2.setText("1");
        m.text3.setText("2022");
        m.text4.setText("327163");
        assertTrue(m.verificare());
    }

    @Test(expected = DetaliiCardIncorecte.class)
    public void verificaretest4() {
        m.text1.setText("1234567891234567");
        m.text2.setText("1");
        m.text3.setText("2019");
        m.text4.setText("327163");
       if(m.verificare() == false)
           throw new DetaliiCardIncorecte();
    }
    
}