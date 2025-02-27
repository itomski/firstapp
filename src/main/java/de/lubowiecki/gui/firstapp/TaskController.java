package de.lubowiecki.gui.firstapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Initializable ist ein Interface, dass garantiert, dass die initialize
// automatisch ausgeführt wird
public class TaskController implements Initializable {

    // Alle Elemente (Eigenschaften und Methoden) mit der Annotation @FXML interagieren mit der Oberfläche

    @FXML // Annotation: Verbindet ein Element in der Oberfläche mit Java
    private ListView<Task> ausgabe;

    @FXML
    private TextField eingabe;

    // TaskService enthält die Verwaltung der Daten
    // Hier ist hinterlegt, wo und wie die Daten gespeichert werden
    // und wie diese beim Start der Anwendung eingelesen werden
    private TaskService service;


    // In eine andere View wechseln
    @FXML
    protected void changeView() throws IOException {
        App.setRoot("next-view"); // Wechsel der View
    }

    // Methode soll auf das Betätigen der Enter-Taste reagieren
    // Wird durch eine Taste ausgelöst
    @FXML
    protected void save(KeyEvent event) { // KeyEvent steht für das Betätigen irgendeiner Taste
        if(event.getCode() == KeyCode.ENTER) { // Prüft, ob die Enter-Taste betätigt wurde
            savePlain();
        }
    }

    @FXML
    protected void savePlain() { // Wird durch einen Button-Click ausgelöst
        String input = eingabe.getText(); // Text aus dem Formularelement holen
        if (input.trim().isEmpty()) return; // Leere Eingabe ignorieren

        eingabe.clear(); // Textfeld wird geleert
        Task task = new Task(input);
        try {
            service.add(task); // Der Task wird zu der Liste hinzugefügt
            showTask(); // Ausgabe wird aktuallisiert
        }
        catch(Exception e) {
            // TODO: Ausgabe der Meldung in der GUI
        }
    }

    @FXML
    protected void toggleDone(KeyEvent event) { // KeyEvent steht für das Betätigen irgendeiner Taste
        if(event.getCode() == KeyCode.SPACE) { // Prüft, ob die Leertaste betätigt wurde
            Task ausgewaehlt = ausgabe.getSelectionModel().getSelectedItem(); // Ausgewähltes Element liefern
            if(ausgewaehlt != null) {
                ausgewaehlt.toggleDone(); // Zustand des Tasks ändern
                ausgabe.refresh(); // ListView: Anzeige aktuallisieren
                try {
                    service.save(); // Im Service muss die Änderung gespeichert werden
                }
                catch (Exception e) {
                    // TODO: Ausgabe der Meldung in der GUI
                }
            }
        }
    }

    // Aktuallisierung der Anzeige der Task-Liste
    private void showTask() {
        // (Alt)daten werden vom Service abgefragt und in der ListView angezeigt

        // FXCollections ist eine Utility-Klasse mit Werkzeugen für GUI-Datenstrukturen
        // .observableList(...) wandelt eine normale Liste ist eine Liste mit Auswahlmöglichkeit
        // so dass mit einem Click etwas in der ListView ausgewählt werden kann
        ausgabe.setItems(FXCollections.observableList(service.getAll()));
    }

    // Wird automatisch ausgeführt, wenn die Oberfläche bereitgestellt wird
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = new TaskService();
        showTask(); // Altdaten anzeigen
    }
}