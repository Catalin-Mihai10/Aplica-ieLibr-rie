package Controllers;

import Exceptii.UsernameSauParolaGresite;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PaginaAutentificareManagerTest extends ApplicationTest {

    private PaginaAutentificareManager c;

    @Before
    public void setUp() throws Exception {
        c = new PaginaAutentificareManager();
        c.username = new TextField();
        c.parola = new PasswordField();
        c.username.setText("admin");
        c.parola.setText("admin");
    }

    @Test
    public void verificareManagerTest() {
        assertTrue(c.verificareManager());
    }

    @Test
    public void verificareManagerTest1() {
        c.username.setText("dsa");
        assertFalse(c.verificareManager());
    }

    @Test(expected = UsernameSauParolaGresite.class)
    public void verificareManagerTest2() throws UsernameSauParolaGresite {
        c.username.setText("dsa");
        if(c.verificareManager() == false)
            throw new UsernameSauParolaGresite();
    }
}