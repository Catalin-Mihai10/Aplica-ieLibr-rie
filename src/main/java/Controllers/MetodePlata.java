package Controllers;

import Exceptii.NuSaAdaugatCarte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class MetodePlata extends ControllerGeneral{

    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    private JSONArray listaJson = new JSONArray();
    private JSONArray listaAchizitii = new JSONArray();

    public void getFromCos(){
        listaJson.clear();
        Calendar data1 = Calendar.getInstance();
        data1.setTime(new Date());
        Random r = new Random();
        int random = r.nextInt(7-1)+1;
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Cos.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                if(obj.get("Username:").toString().equals(PaginaLogIn.getNume())) {
                    obj.put("Data:",data1.get(Calendar.DATE)+"-"+data1.get(Calendar.MONTH)+"-"+data1.get(Calendar.YEAR));
                    obj.put("Estimare:",random);
                    listaJson.add(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void Citeste(){
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Achizitii.json")) {

            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                listaAchizitii.add(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {
        String s = "CosDeCumparaturi.fxml";
        redirectioneazaPagina(actionEvent,s);
    }

    public void plataCard(ActionEvent actionEvent) {
        if(!text1.getText().isEmpty()&&!text2.getText().isEmpty()) {
            String s = "PlataCard.fxml";
            redirectioneazaPagina(actionEvent,s);
        }else{
            String m = "Campurile trebuie\ncompletate!";
            redirectionareEroare(m);
        }
    }

    public void finalizare(ActionEvent actionEvent) {
        if(!text1.getText().isEmpty()&&!text2.getText().isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("ComandaFinalizata.fxml"));
                AnchorPane paginaA = loader.load();
                adaugaAchizitii();
                stergereCos();
                Scene scene = new Scene(paginaA);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String m = "Campurile trebuie\ncompletate!";
            redirectionareEroare(m);
        }
    }

    public void rangSiStoc() throws IOException {
        JSONArray aux = new JSONArray();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Carti.json")) {
            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                Iterator<JSONObject> il = listaJson.iterator();
                while (il.hasNext()) {
                    JSONObject obiect = il.next();
                    if (obj.get("Titlu:").toString().equals(obiect.get("Titlu:").toString())) {
                        int val = Integer.parseInt(obj.get("Rang:").toString());
                        val++;
                        obj.put("Rang:", val);
                        int stoc = Integer.parseInt(obj.get("Stoc:").toString());
                        if (stoc != 0) {
                            stoc--;
                        }
                        obj.put("Stoc:", stoc);
                    }
                }
                aux.add(obj);
            }
            try (FileWriter fisier = new FileWriter(getUserPath()+"\\resources\\main\\Carti.json")) {
                fisier.write(aux.toJSONString());
                fisier.flush();
            } catch (IOException e) {
                throw new NuSaAdaugatCarte();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        String fisier = "\\Carti.json";
        copiaza(fisier);
    }

    public void adaugaAchizitii() throws IOException {
        getFromCos();
        Citeste();
        rangSiStoc();
        listaAchizitii.addAll(listaJson);
        try (FileWriter fisier = new FileWriter(getUserPath()+"\\resources\\main\\Achizitii.json")) {
            fisier.write(listaAchizitii.toJSONString());
            fisier.flush();
        } catch (IOException e) {
            throw new NuSaAdaugatCarte();
        }
        String fisier = "\\Achizitii.json";
        copiaza(fisier);
    }

    public void stergereCos() throws IOException {
        listaJson.clear();
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(getUserPath()+"\\resources\\main\\Cos.json")) {
            JSONArray temp = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> it = temp.iterator();
            while (it.hasNext()) {
                JSONObject obj = it.next();
                if(!obj.get("Username:").toString().equals(PaginaLogIn.getNume())) {
                    listaJson.add(obj);
                }
            }
            try (FileWriter fisier = new FileWriter(getUserPath()+"\\resources\\main\\Cos.json")) {
                fisier.write(listaJson.toJSONString());
                fisier.flush();
            } catch (IOException e) {
                throw new NuSaAdaugatCarte();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String fisier = "\\Cos.json";
        copiaza(fisier);
    }
}
