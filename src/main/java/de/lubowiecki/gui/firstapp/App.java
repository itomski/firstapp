package de.lubowiecki.gui.firstapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main-view"));
        stage.setTitle("Firstapp");
        stage.setScene(scene);
        stage.show();
    }

    // package-private, damit es auch in den Controllern sichtbar ist
    static void setRoot(String viewName) throws IOException {
        scene.setRoot(loadFXML(viewName));
    }

    private static Parent loadFXML(String viewName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(viewName + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}