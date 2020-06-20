package Controllers;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PaginaLogInTest extends ApplicationTest {

    private PaginaLogIn c;

    @Before
    public void setUp() throws Exception {
        c = new PaginaLogIn();
        c.username = new TextField();
        c.parola = new PasswordField();
    }

    @Test
    public void verificareUtilizatorTest() {
        c.username.setText("Ion");
        c.parola.setText("ionel");
        assertTrue(c.verificareUtilizator());
    }

    @Test
    public void verificareUtilizatorTest2() {
        c.username.setText("Mirel");
        c.parola.setText("mircea");
        assertFalse(c.verificareUtilizator());
    }
}