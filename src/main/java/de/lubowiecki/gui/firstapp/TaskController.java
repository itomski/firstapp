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

public class TaskController implements Initializable {

    @FXML // Annotation: Verbindet ein Element in der Oberfläche mit Java
    private ListView<Task> ausgabe;

    @FXML
    private TextField eingabe;

    private TaskService service;

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
                    service.save();
                }
                catch (Exception e) {
                    // TODO: Ausgabe der Meldung in der GUI
                }
            }
        }
    }

    private void showTask() {
        /*
        StringBuilder sb = new StringBuilder();
        for(Task t : tasks) {
            sb.append(t.getTitle()).append("\n");
        }
        ausgabe.setText(sb.toString());
        */
        ausgabe.setItems(FXCollections.observableList(service.getAll()));
    }

    // Wird automatisch ausgeführt, wenn die Oberfläche bereitgestellt wird
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = new TaskService();
        showTask(); // Altdaten anzeigen
    }
}