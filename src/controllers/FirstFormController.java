package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.ConnectionPool;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstFormController {
    Stage stage;

    public FirstFormController(Stage stage){
        this.stage=stage;
    }


    @FXML
    private TextField konobarKorisnickoImeTextField;

    @FXML
    private PasswordField konobarLozinkaPasswordField;

    @FXML
    private Button prijavaKonobarButton;

    @FXML
    private Label prijavaKonobarLabela;

    @FXML
    private TextField kuvarKorisnickoImeTextField;

    @FXML
    private PasswordField kuvarPassvordField;

    @FXML
    private Button prijavaKuvarButton;

    @FXML
    private Label kuvarPrijavaLabela;

    @FXML
    void showKonobarForm(MouseEvent event) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call get_konobar_by_username_and_password(?, ?)}");
            callableStatement.setString(1, konobarKorisnickoImeTextField.getText());
            callableStatement.setString(2, konobarLozinkaPasswordField.getText());
            resultSet = callableStatement.executeQuery();
            if(resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "konobarForm.fxml"));
                KonobarController konobarController = new KonobarController(stage, resultSet.getString(1));
                loader.setController(konobarController);
                Parent root=null;
                root = loader.load();
                stage.setTitle("Restoran");
                stage.setScene(new Scene(root));
                stage.show();
            }
            else{
                konobarKorisnickoImeTextField.setText("");
                konobarLozinkaPasswordField.setText("");
                prijavaKonobarLabela.setText("Nepostojece korisnicko ili lozinka");

            }
        }
        catch (IOException|SQLException exception){
            exception.printStackTrace();
        }finally {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (callableStatement != null)
            try {
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        ConnectionPool.getInstance().checkIn(connection);
    }

    }

    @FXML
    void showKuvarForm(MouseEvent event) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().checkOut();

            callableStatement = connection.prepareCall("{call get_kuvar_by_username_and_password(?, ?)}");
            callableStatement.setString(1, kuvarKorisnickoImeTextField.getText());
            callableStatement.setString(2, kuvarPassvordField.getText());
            resultSet = callableStatement.executeQuery();
            if(resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "kuvarForm.fxml"));
                KuvarController kuvarController = new KuvarController(stage, resultSet.getString(1));
                loader.setController(kuvarController);
                Parent root=null;
                root = loader.load();
                stage.setTitle("Restoran");
                stage.setScene(new Scene(root));
                stage.show();
            }
            else{
                kuvarKorisnickoImeTextField.setText("");
                kuvarPassvordField.setText("");
                kuvarPrijavaLabela.setText("Nepostojece korisnicko ili lozinka");

            }
        }
        catch (IOException|SQLException exception){
            exception.printStackTrace();
        }finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (callableStatement != null)
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            ConnectionPool.getInstance().checkIn(connection);
        }

    }
}
