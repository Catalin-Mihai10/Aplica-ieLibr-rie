package Controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;


public class PaginaInregistrareTest extends ApplicationTest{

    private PaginaInregistrare p;

    @Before
    public void setUp() throws Exception {
        p = new PaginaInregistrare();
        p.username = new TextField();
        p.parola = new PasswordField();
        p.lista = new JSONArray();
        p.parola.setText("Ion");
        p.username.setText("ionel");
    }


    @Test
    public void existaUtilizatorTest() {
        assertTrue(p.ExistaUtilizator("Ion"));
    }

    @Test
    public void existaUtilizatorTest2() {
        assertFalse(p.ExistaUtilizator("Becali"));
    }

    @Test
    public void existaUtilizatorTest3() {
        assertFalse(p.ExistaUtilizator(""));
    }

    @Test
    public void citesteFisier() {
        p.CitesteFisier();
        assertEquals(5,p.lista.size());
    }

}