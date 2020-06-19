package Controllers;

import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class EroareTest extends ApplicationTest {

    private Eroare c;
    private String s;

    @Before
    public void setUp() throws Exception {
        c = new Eroare();
        c.text = new Text();
        s = "Eroare";
    }

    @Test
    public void setText() {
        c.setText(s);
        assertEquals("Eroare",c.text.getText());
    }
}