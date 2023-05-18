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
import models.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import javafx.scene.input.MouseEvent;

public class KonobarNarudzbaController {

    private Stage stage;
    private String JMB;
    int idNarudzbe;

    public static boolean novaNarudzba=false;
    public static ArrayList<Integer> noveNarudzbe= new ArrayList<>();

    @FXML
    private ComboBox<String> hranaComboBox;

    @FXML
    private TextField kolicinaTextField;

    @FXML
    private Button dodajHranuNaNarudzbuButton;

    @FXML
    private ComboBox<String> piceComboBox;

    @FXML
    private ListView<String> narudzbaListView;

    @FXML
    private Label selected;

    @FXML
    private Spinner<Integer> kolicinaSpinner;

    @FXML
    private Spinner<Integer> piceSpinner;

    @FXML
    private Button dodajPiceButton;

    @FXML
    private Button kreirajNarudzbuButton;

    @FXML
    private ComboBox<String> brojStolaComboBox;

    @FXML
    private Button prethodnaStranaButton;


    @FXML
    private Button dodajNaIzabranuNarudzbu;



    public KonobarNarudzbaController(Stage stage, String JMB) {
        this.stage = stage;
        this.JMB=JMB;
    }

    public KonobarNarudzbaController(Stage stage, String JMB, int id) {
        this.stage = stage;
        this.JMB=JMB;
        this.idNarudzbe=id;
    }

    ArrayList<Hrana> hranaArrayList = Hrana.getHrana();
    ArrayList<Pice> piceArrayList=Pice.getPice();
    ArrayList<Pice> piceNarudzba =new ArrayList<>();
    ArrayList<Hrana> hranaNarudzba =new ArrayList<>();
    ArrayList<Object> narudzbaArrayList=new ArrayList<>();
    ArrayList<Integer> kolicinaStavkiPica=new ArrayList<>();
    ArrayList<Integer> kolicinaStavkiHrane=new ArrayList<>();
    ArrayList<Sto> stoArrayList1= new ArrayList<>();
    String DUPLI_ARTIKAL="Ne moze se dva puta dodati isti artikal";
    ArrayList<String> narudzbaLista=new ArrayList<>();





    public void initialize() {

        for (int i = 0; i < hranaArrayList.size(); i++) {
            String hrana = "";
            hrana = hranaArrayList.get(i).getNaziv() + "   " + hranaArrayList.get(i).getCijena() + "   " + "KM";
            hranaComboBox.getItems().add(i, hrana);}
        for (int i = 0; i < piceArrayList.size(); i++) {
            String pice = "";
            pice = piceArrayList.get(i).getNaziv() + "   " + piceArrayList.get(i).getCijena() + "   " + "KM";
            piceComboBox.getItems().add(i, pice);}

        kolicinaSpinner.setEditable(true);
        kolicinaSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));

        piceSpinner.setEditable(true);
        piceSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));

        ArrayList<Sto> stoArrayList=Sto.stoloviKonobara(JMB);
        narudzbaListView.setEditable(true);

        for(int i =0; i<stoArrayList.size(); i++){

            brojStolaComboBox.getItems().add(i, Integer.toString(stoArrayList.get(i).getBrojStola()));

        }
        stoArrayList1=stoArrayList;


    }



    @FXML
    void dodajHranuNaNarudzbu(MouseEvent event){

        try {
            int k = hranaComboBox.getSelectionModel().getSelectedIndex();
            if(kolicinaSpinner.getValue()>0) {
                for (int i=0;i <hranaNarudzba.size(); i++){
                    if (hranaNarudzba.get(i).getId() == hranaArrayList.get(k).getId()){
                        throw new IllegalArgumentException();
                    }
                }
                narudzbaArrayList.add( hranaArrayList.get(k));
                kolicinaStavkiHrane.add(kolicinaSpinner.getValue());
                hranaNarudzba.add(hranaArrayList.get(k));
                narudzbaLista.add("H    " + hranaComboBox.getSelectionModel().getSelectedItem().toString() + "    x " + kolicinaSpinner.getValue().toString());
                adOnListView();

            }
        } catch (NullPointerException nullPointerException) {

            selected.setText("Morate izabrati artikal");
        }
        catch (IllegalArgumentException illegalArgumentException){
            selected.setText("Ne moze se 2 puta dodati isti artikal");



        }
    }


    int numPica=0;
    @FXML
    void dodajPiceNaNarudzbu(MouseEvent event) {
        try {

            int k = piceComboBox.getSelectionModel().getSelectedIndex();

            if(piceSpinner.getValue()>0) {
                if(piceSpinner.getValue() > piceArrayList.get(k).getKolicina()){
                    throw new IOException();
                }
                kolicinaStavkiPica.add(piceSpinner.getValue());
                for (int i=0;i <piceNarudzba.size(); i++){
                    if (piceNarudzba.get(i).getId() == piceArrayList.get(k).getId()){
                        throw new IllegalArgumentException();
                    }
                }
                piceNarudzba.add(piceArrayList.get(k));
                narudzbaArrayList.add(piceArrayList.get(k));
                narudzbaLista.add("P    " + piceComboBox.getSelectionModel().getSelectedItem().toString() + "    x " + piceSpinner.getValue().toString());
                adOnListView();
            }



        } catch (NullPointerException nullPointerException) {

            selected.setText("Morate izabrati artikal");
        }
        catch (IllegalArgumentException illegalArgumentException){
            selected.setText("Ne moze se 2 puta dodati isti artikal");
        }
        catch (IOException ioException){
            selected.setText("Nema toliko elemenata na stanju");
        }



    }

    @FXML
    void obrisiSelektovanustavku(MouseEvent event) {

        int k= narudzbaListView.getSelectionModel().getSelectedIndex();
        String selectedItems =  narudzbaListView.getSelectionModel().getSelectedItem();

        if (Character.compare(selectedItems.charAt(0),'P') == 0) {
            Pice obrisanoPice=(Pice) narudzbaArrayList.get(k);
            narudzbaArrayList.remove(k);
            narudzbaLista.remove(k);
            narudzbaListView.getItems().remove(selectedItems);
            for (int i=0; i<piceNarudzba.size(); i++){
                if (piceNarudzba.get(i).getId()==obrisanoPice.getId()){
                    piceNarudzba.remove(i);
                    kolicinaStavkiPica.remove(i);
                }

            }

        }

                else  {
                    Hrana obrisanaHrana=(Hrana) narudzbaArrayList.get(k);
                    narudzbaArrayList.remove(k);
                    narudzbaLista.remove(k);
                    narudzbaListView.getItems().remove(selectedItems);
                    for (int i=0; i<hranaNarudzba.size(); i++){
                        if (hranaNarudzba.get(i).getId()==obrisanaHrana.getId()){
                            hranaNarudzba.remove(i);
                            kolicinaStavkiHrane.remove(i);

                        }

                    }
                }
    }
    @FXML
    void kreirajNarudzbu(MouseEvent event) {
        int idIzabranogStola = -1;
        idIzabranogStola= brojStolaComboBox.getSelectionModel().getSelectedIndex();
        if (idIzabranogStola == -1)
        {
            selected.setText("Morate izabrati broj stola");
        }
        else
        {
            Narudzba narudzba = new Narudzba(JMB, stoArrayList1.get(idIzabranogStola).getId());
            int idKreiraneNarudbe = narudzba.addNarudzba();
            for (int i = 0; i < piceNarudzba.size(); i++) {
                NarudzbaPice narudzbaPice = new NarudzbaPice(idKreiraneNarudbe, piceNarudzba.get(i).getId(), piceNarudzba.get(i).getCijena(), kolicinaStavkiPica.get(i).intValue());
                narudzbaPice.addNarudzbaPice();
            }
            for (int i = 0; i < hranaNarudzba.size(); i++) {
                NarudzbaHrana narudzbaHrana= new NarudzbaHrana(idKreiraneNarudbe, hranaNarudzba.get(i).getId(), hranaNarudzba.get(i).getCijena(), kolicinaStavkiHrane.get(i).intValue());
                narudzbaHrana.addNarudzbaHrana();
        }

            if( hranaNarudzba.size() >0) {novaNarudzba=true;}
            piceNarudzba.clear();
            hranaNarudzba.clear();
            narudzbaLista.clear();
            hranaNarudzba.clear();
            selected.setText("");
            adOnListView();

        }
    }

    @FXML
    void prikaziPrethodnuStranu(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "konobarForm.fxml"));

        KonobarController controller = new KonobarController(stage, JMB);
        loader.setController(controller);

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        stage.setTitle("Restoran");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void adOnListView(){
        ObservableList<String> items = FXCollections.observableArrayList(narudzbaLista);
        narudzbaListView.setItems(items);
    }

    @FXML
    void dodajNaIzabranuNarudzbu(MouseEvent event) {
        for (int i = 0; i < piceNarudzba.size(); i++) {
            System.out.println(NarudzbaPice.daLiPicePostojiNaNarudzbi(idNarudzbe,piceNarudzba.get(i).getId()));

            if (NarudzbaPice.daLiPicePostojiNaNarudzbi(idNarudzbe,piceNarudzba.get(i).getId()) ==-1){
            NarudzbaPice narudzbaPice = new NarudzbaPice(idNarudzbe, piceNarudzba.get(i).getId(), piceNarudzba.get(i).getCijena(), kolicinaStavkiPica.get(i).intValue());
            narudzbaPice.addNarudzbaPice();}
            else{
                NarudzbaPice.updateNarudzbaPice(idNarudzbe,piceNarudzba.get(i).getId(),kolicinaStavkiPica.get(i).intValue());
            }
        }
        for (int i = 0; i < hranaNarudzba.size(); i++) {
            if (NarudzbaHrana.daLiPicePostojiNaNarudzbi(idNarudzbe, hranaNarudzba.get(i).getId()) ==-1){
            NarudzbaHrana narudzbaHrana= new NarudzbaHrana(idNarudzbe, hranaNarudzba.get(i).getId(), hranaNarudzba.get(i).getCijena(), kolicinaStavkiHrane.get(i).intValue());
            narudzbaHrana.addNarudzbaHrana();}
            else {
                NarudzbaHrana.updateNarudzbaHrana(idNarudzbe, hranaNarudzba.get(i).getId(), kolicinaStavkiHrane.get(i).intValue());
            }

            Stage stage = (Stage)dodajNaIzabranuNarudzbu.getScene().getWindow();
            stage.close();



    }


}
}






