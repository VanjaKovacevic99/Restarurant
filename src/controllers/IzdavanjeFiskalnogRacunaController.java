package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.FiskalniRacun;
import models.Narudzba;
import models.Sto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class IzdavanjeFiskalnogRacunaController {
    Stage stage;
    String JMB;

    public IzdavanjeFiskalnogRacunaController(Stage stage, String JMB){
        this.stage=stage;
        this.JMB=JMB;
    }
    @FXML
    private Button prikaziNarudzbuButton;

    @FXML
    private ListView<String> prikazNarudzbiListView;

    @FXML
    private Label selected;

    @FXML
    private Button nazadButton;

    @FXML
    private Button dodajButton;

    @FXML
    private Button izdajRacunButton;

    @FXML
    private ComboBox<String> izborNarudzbeComboBox;
    ArrayList<Narudzba> sveNarudzbe=Narudzba.getAllNarudzbe();


    public void initialize(){

            String narudzbaBrojStoja="";
            for (int i=0; i<sveNarudzbe.size(); i++){
            narudzbaBrojStoja= Integer.toString(sveNarudzbe.get(i).getId())+ "  Broj stola  " + Sto.getBrStolaNaOsnovuId(sveNarudzbe.get(i).getIdStola());
            izborNarudzbeComboBox.getItems().add(i,narudzbaBrojStoja);}
        }



    @FXML
    void izdajRacun(MouseEvent event) {
        java.util.Date utilDate = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        cal.set(Calendar.MILLISECOND, 0);
        int idSelektovaneNarudzbe=izborNarudzbeComboBox.getSelectionModel().getSelectedIndex();
        int idIzabraneNarudzbe= sveNarudzbe.get(idSelektovaneNarudzbe).getId();
        FiskalniRacun fiskalniRacun=new FiskalniRacun(1, new java.sql.Timestamp(utilDate.getTime()), new java.sql.Timestamp(cal.getTimeInMillis()),idIzabraneNarudzbe);
        if (Narudzba.daLiJeZaNarudzbuIzdanRacun(idIzabraneNarudzbe) == -1){
            selected.setText(" Za narudzbu je vec kreiran fiskalni racun");
        }
        else {
         selected.setText(null);
        fiskalniRacun.addFiskalniRacun();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message ");
            alert.setContentText("Racun kreiran");
            alert.show();
            sveNarudzbe.remove(idSelektovaneNarudzbe);
        prikazNarudzbiListView.setItems(null);}

    }

    @FXML
    void prikaziNarudzbu(MouseEvent event) {
        selected.setText("");
        ArrayList<String> listaPica= sveNarudzbe.get(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex()).listaPicaSaNarudzbe();
        ArrayList<String> listaHrane= sveNarudzbe.get(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex()).listaHraneSaNarudzbe();

        for (int i=0; i<listaHrane.size(); i++ ){
           listaPica.add(listaHrane.get(i));
       }

        ObservableList<String> items = FXCollections.observableArrayList(listaPica);
        prikazNarudzbiListView.setItems(items);

    }

    @FXML
    void showKonobarForm(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "konobarForm.fxml"));
            KonobarController konobarController = new KonobarController(stage, JMB);
            loader.setController(konobarController);
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
}
