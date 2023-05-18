package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Narudzba;
import models.Sto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PregledNarudzbi {

    Stage stage;
    String JMB;

    @FXML
    private Button prikaziNeodnesenuNarudzbuButton;

    @FXML
    private ListView<String> prikazNarudzbiListView;

    @FXML
    private Button oznaciOdnesenuNarudzbuButton;

    @FXML
    private ComboBox<String>  izborNarudzbeComboBox;

    @FXML
    private Button nazadButton;

    public PregledNarudzbi(Stage stage, String JMB){
        this.stage=stage;
        this.JMB=JMB;
    }

    ArrayList<Narudzba> neodneseneNarudzbe1=Narudzba.neodneseneNarudzbeKonobara(JMB);
    ArrayList<String> stoNarudzbaLista=new ArrayList<>();
    ObservableList<String> items1;
    int indeksSelektovaneNarudzbe=0;



    public void initialize(){
        ArrayList<Narudzba> neodneseneNarudzbe=Narudzba.neodneseneNarudzbeKonobara(JMB);
        for (int j=0; j<neodneseneNarudzbe.size() ; j++){
            String narudzbaBrojStola="";
            narudzbaBrojStola= Integer.toString(neodneseneNarudzbe.get(j).getId())+ "  Broj stola  " + Sto.getBrStolaNaOsnovuId(neodneseneNarudzbe.get(j).getIdStola());
            izborNarudzbeComboBox.getItems().addAll(narudzbaBrojStola);
        };
        neodneseneNarudzbe1=neodneseneNarudzbe;;

    }
    public void reload(){

        for (int j=0; j<neodneseneNarudzbe1.size() ; j++){
            String narudzbaBrojStola="";
            narudzbaBrojStola= Integer.toString(neodneseneNarudzbe1.get(j).getId())+ "  Broj stola  " + Sto.getBrStolaNaOsnovuId(neodneseneNarudzbe1.get(j).getIdStola());
            izborNarudzbeComboBox.getItems().addAll(narudzbaBrojStola);
        };
    }




    @FXML
    void prikaziNeodnesenuNarudzbu(MouseEvent event) {

        indeksSelektovaneNarudzbe=izborNarudzbeComboBox.getSelectionModel().getSelectedIndex();
        ArrayList<String> listaPica=neodneseneNarudzbe1.get(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex()).listaPicaSaNarudzbe();
        ArrayList<String> listaHrane=neodneseneNarudzbe1.get(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex()).listaHraneSaNarudzbe();
        for (int i=0; i<listaHrane.size(); i++ ){
            listaPica.add(listaHrane.get(i));
        }
        ObservableList<String> items = FXCollections.observableArrayList(listaPica);
        prikazNarudzbiListView.setItems(items);

    }


    @FXML
    void odnesenaNarudzba(MouseEvent event) {
        neodneseneNarudzbe1.get(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex()).oznaciOdnesenuNarudzbu();
        neodneseneNarudzbe1.remove(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex());
        izborNarudzbeComboBox.getItems().remove(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex());
        prikazNarudzbiListView.setItems(null);
       // reload();


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

    @FXML
    void dodajNaNarudzbu(MouseEvent event) {
        try {
            if(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex() != -1)
            {Stage newStage=new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "konobarNarudzbaForm.fxml"));
            KonobarNarudzbaController konobarNarudzbaController = new KonobarNarudzbaController(stage, JMB, neodneseneNarudzbe1.get(izborNarudzbeComboBox.getSelectionModel().getSelectedIndex()).getId());
            loader.setController(konobarNarudzbaController);
            Parent root=null;
            root = loader.load();
            newStage.setTitle("Restoran");
            newStage.setScene(new Scene(root));
            newStage.show();}

        }
        catch (IOException exception){
            exception.printStackTrace();
        }

    }
}

