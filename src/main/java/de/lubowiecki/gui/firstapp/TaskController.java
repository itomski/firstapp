package de.lubowiecki.gui.firstapp;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class TaskController {

    @FXML // Annotation: Verbindet ein Element in der Oberfläche mit Java
    private ListView<Task> ausgabe;

    @FXML
    private TextField eingabe;

    private List<Task> tasks = new ArrayList<>();

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
        tasks.add(task); // Der Task wird zu der Liste hinzugefügt
        showTask(); // Ausgabe wird aktuallisiert
    }

    private void showTask() {
        /*
        StringBuilder sb = new StringBuilder();
        for(Task t : tasks) {
            sb.append(t.getTitle()).append("\n");
        }
        ausgabe.setText(sb.toString());
        */

        ausgabe.setItems(FXCollections.observableList(tasks));
    }
}