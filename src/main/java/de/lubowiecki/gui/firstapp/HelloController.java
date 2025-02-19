package de.lubowiecki.gui.firstapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML // Annotation: Verbindet ein Element in der Oberfläche mit Java
    private Label ausgabe;

    @FXML
    private TextField eingabe;

    private List<Task> tasks = new ArrayList<>();

    @FXML
    protected void save() {

        String input = eingabe.getText(); // Text aus dem Formularelement holen
        if(input.trim().isEmpty()) return; // Leere Eingabe ignorieren

        eingabe.clear(); // Textfeld wird geleert
        Task task = new Task(input);
        tasks.add(task); // Der Task wird zu der Liste hinzugefügt
        showTask(); // Ausgabe wird aktuallisiert
    }

    private void showTask() {
        StringBuilder sb = new StringBuilder();
        for(Task t : tasks) {
            sb.append(t.getTitle()).append("\n");
        }
        ausgabe.setText(sb.toString());
    }
}