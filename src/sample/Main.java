package sample;

import controllers.FirstFormController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(".." + File.separator + "views" + File.separator + "firstForm.fxml"));
        FirstFormController firstFormController=new FirstFormController(primaryStage);
        loader.setController(firstFormController);
        Parent root = loader.load();
        primaryStage.setTitle("Restoran");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
