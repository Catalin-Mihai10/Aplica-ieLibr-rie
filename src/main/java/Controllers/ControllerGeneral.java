package Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ControllerGeneral {

    public void redirectioneazaPagina(ActionEvent actionEvent,String s){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource(s));
            AnchorPane pagina = loader.load();
            Scene scene = new Scene(pagina);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectionareEroare(String m){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("Eroare.fxml"));
            AnchorPane paginaA = loader.load();
            Eroare controller = loader.getController();
            controller.setText(m);
            Scene scene = new Scene(paginaA);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> citireJson(ListView<String> lista, String s, ObservableList<String> observ){
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath("Carti.json"))) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obiect = it.next();
                if(obiect.get("Categorie:").toString().equals(s)){
                    observ.add(obiect.get("Titlu:").toString());
                }
            }
            lista.setItems(observ);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return observ;
    }

    public String getUserPath(String str){
        String s = new File(System.getProperty("user.dir")).getParentFile().toString();
        Path p = Paths.get(s);
        String l;
        if(p.getFileName().toString().equals("build")){
            Path m = Paths.get(s, "");
            l = m.resolve(Paths.get("","resources","main",str)).toString();
        }
        else {
            String f = "/AplicatieLibrarie";
            Path m = Paths.get(s, f);
            l = (m.resolve(Paths.get("build", "resources", "main", str)).toString());
        }
        return l;
    }

    public void copiaza(String fisierul) throws IOException {
        FileInputStream instream = null;
        FileOutputStream outstream = null;

        try{
            File infile =new File(getUserPath(fisierul));
            File s = new File(System.getProperty("user.dir")).getParentFile().getParentFile();
            File outfile =new File(s+"\\src\\main\\resources\\"+fisierul);

            instream = new FileInputStream(infile);
            outstream = new FileOutputStream(outfile);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = instream.read(buffer)) > 0){
                outstream.write(buffer, 0, length);
            }

            System.out.println("File copied successfully!!");

        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally{
            instream.close();
            outstream.close();
        }
    }
}
