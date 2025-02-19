package de.lubowiecki.gui.firstapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    // DesignPattern = Entwurfsmuster
    // Standardlösungen für typische Probleme der Softwareentwiklung

    // MVC - Model View Controller
    // Model = Daten und Datenhaltung
    // View = Oberfläche, Darstellung (fxml)
    // Controller = Steuerung, Routing (HelloController)

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Firstapp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}