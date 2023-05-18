package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class KonobarController {

    Stage stage;
    String JMB;
    public KonobarController(Stage stage, String JMB){
        this.stage=stage;
        this.JMB=JMB;
    }

    @FXML
    private Button narudzbeButton;

    @FXML
    private Button showPregledNarudzbiComboBox;

    @FXML
    private Button odjavaButton;

    @FXML
    private ImageView konobarImageView;

    @FXML
    private ListView<String> napraviteNarudzbeLisView;

    @FXML
    private Label napravitaNarudzba;

    @FXML
    private Button vidjeneNoveNarudzbeButton;

    @FXML
    private Button izdavanjeRacunaButton;

    Image iconWithNoNotifications= new Image(KuvarController.class.getResourceAsStream("/images/bell.png"));
    Image iconWithNotifications= new Image(KuvarController.class.getResourceAsStream("/images/bellSaNotifikacijom.png"));

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
    void showNarudzbaForm(MouseEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "konobarNarudzbaForm.fxml"));
        KonobarNarudzbaController konobarNarudzbaController = new KonobarNarudzbaController(stage, JMB);
        loader.setController(konobarNarudzbaController);
        Parent root=null;
        root = loader.load();
        stage.setTitle("Narudzba");
        stage.setScene(new Scene(root));
        stage.show();

        }
        catch (IOException exception){
            exception.printStackTrace();
        }


    }

    @FXML
    void showPregledNarudzbi(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "pregledNarudzbiForm.fxml"));
            PregledNarudzbi pregledNarudzbi = new PregledNarudzbi(stage, JMB);
            loader.setController(pregledNarudzbi);
            Parent root=null;
            root = loader.load();
            stage.setTitle("Narudzbe");
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (IOException exception){
            exception.printStackTrace();
        }

    }
    public void initialize() throws MalformedURLException {

        if ( KuvarController.novaNapravitaNarudzba == false)
        {konobarImageView.setImage(iconWithNoNotifications);
        }
        else {
            konobarImageView.setImage(iconWithNotifications);
        }

    }

    @FXML
    void showNoveNapraviteNarudzbe(MouseEvent event) {

        napravitaNarudzba.setText("Gotove narudzbe");
        ObservableList<String> items = FXCollections.observableArrayList(KuvarController.napraviteNarudzbe);
        napraviteNarudzbeLisView.setItems(items);
        konobarImageView.setImage(iconWithNoNotifications);

    }

    @FXML
    void showIzdavanjeRacunaForm(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "izdavanjeFiskalnogRacunaForm.fxml"));
            IzdavanjeFiskalnogRacunaController izdavanjeFiskalnogRacunaController = new IzdavanjeFiskalnogRacunaController(stage, JMB);
            loader.setController(izdavanjeFiskalnogRacunaController);
            Parent root=null;
            root = loader.load();
            stage.setTitle("Fiskalni racun");
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (IOException exception){
            exception.printStackTrace();
        }

    }

    @FXML
    void ukloniNapravituNarudzbu(MouseEvent event) {
        napraviteNarudzbeLisView.getItems().remove(napraviteNarudzbeLisView.getSelectionModel().getSelectedItem());


    }



}
