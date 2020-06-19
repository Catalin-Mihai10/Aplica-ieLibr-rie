package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ControllerGeneralTest extends ApplicationTest {

    private ControllerGeneral c;
    private ObservableList<String> o;
    private ObservableList<String> o2;
    private ListView<String> l;
    private File f2;


    @Before
    public void setUp() throws Exception{
        c = new ControllerGeneral();
        o = FXCollections.observableArrayList("Ender's Game","The Soldier","Metro 2033","Metro 2034","Doctor Who");
        o2 = FXCollections.observableArrayList();
        f2 = new File("src/test/resources/user-test.json");
        l = new ListView<>();
    }

    @Test
    public void citireJsontest1() {
        assertEquals(o,c.citireJson(l,"SF",o2));
    }

    @Test
    public void citireJsontest2() {
        assertNotEquals(o,c.citireJson(l,"Romance",o2));
    }

    @Test
    public void getUserPath() {
        String s = new File(System.getProperty("user.dir")).getParent();
        assertEquals(s,c.getUserPath());
    }

    @Test
    public void copiazatest1() throws IOException {
        String temp = "/user-test.json";
        c.copiaza(temp);
        File f1 = new File("src/main/resources/user.json");
        assertEquals("The files differ!",
                FileUtils.readFileToString(f1, "utf-8"),
                FileUtils.readFileToString(f2, "utf-8"));
    }

}