package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Narudzba;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class KuvarController {

    Stage stage;
    String JMB;


    @FXML
    private Button ucitajButton;

    @FXML
    private ImageView notifikacijaImageView;

    @FXML
    private ListView<String> narudzbeListView;

    @FXML
    private Button odjavaButton;

    @FXML
    private Button napravljenaNarudzbaButton;

    Image iconWithNoNotifications= new Image(KuvarController.class.getResourceAsStream("/images/bell.png"));
    Image iconWithNotifications= new Image(KuvarController.class.getResourceAsStream("/images/bellSaNotifikacijom.png"));
    public static boolean novaNapravitaNarudzba = false;
    public static ArrayList<String> napraviteNarudzbe = new ArrayList<>();

    public KuvarController(Stage stage, String JMB){

        this.stage=stage;
        this.JMB=JMB;
    }




    public void initialize() throws MalformedURLException {


        if ( KonobarNarudzbaController.novaNarudzba == false)
        {notifikacijaImageView.setImage(iconWithNoNotifications);
        }
        else {
            notifikacijaImageView.setImage(iconWithNotifications);
        }

    }

    @FXML
    void prikaziNoveNarudzbe(MouseEvent event) {
        ArrayList<String> arrayList=new ArrayList<>();
        ArrayList<String> arrayList1=new ArrayList<>();

        for (int i=0; i<KonobarNarudzbaController.noveNarudzbe.size(); i++){
            arrayList=(Narudzba.listaHraneNarudzbe(KonobarNarudzbaController.noveNarudzbe.get(i).intValue()));
            for (int j=0; j<arrayList.size() ; j++)
            {
                arrayList1.add(arrayList.get(j));
            }

        }

        ObservableList<String> items = FXCollections.observableArrayList(arrayList1);
        narudzbeListView.setItems(items);
        notifikacijaImageView.setImage(iconWithNoNotifications);

    }

    @FXML
    void showFirstForm(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "firstForm.fxml"));
            FirstFormController firstFormController= new FirstFormController(stage);
            loader.setController(firstFormController);
            Parent root=null;
            root = loader.load();
            stage.setTitle("Restoran");
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @FXML
    void naruzdbaNapravita(MouseEvent event) {
        novaNapravitaNarudzba=true;
        String string=narudzbeListView.getSelectionModel().getSelectedItems().toString();
        napraviteNarudzbe.add(string.substring(1, string.length()-1));
        narudzbeListView.getItems().remove(narudzbeListView.getSelectionModel().getSelectedItem());

    }


}
