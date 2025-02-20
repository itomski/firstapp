package de.lubowiecki.gui.firstapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private List<Task> tasks;

    // System.getProperty("user.home") ermittelt den Benutzerordner
    private final String FILE = System.getProperty("user.home") + "/tasks.ser";

    public TaskService() {
        /* TODO: Wenn beim Problem eine Anzeige in der GUI gewünscht ist
            muss die Exception an den Controller weitergegeben werden */
        this.tasks = readFromFile(); // Alttdaten werden nur beim Start der Anwendung eingelesen
    }

    public void add(Task task) throws IOException {
        tasks.add(task);
        saveToFile(); // Nach jeder Änderung der Liste muss sie in die Datei gespeichert werden
    }

    public void save() throws IOException {
        saveToFile(); // Nach jeder Änderung der Liste muss sie in die Datei gespeichert werden
    }

    public List<Task> getAll() {
        return tasks;
    }

    // Altdaten rekonstruiren
    private List<Task> readFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (List<Task>) in.readObject(); // Altdaten zurückgeben
        }
        catch(Exception e) { // Wenn etwas schief läuft
            return new ArrayList<>(); // Oder eine leere Liste d.h. Programm bekommt keine Altdaten
        }
    }

    // Daten nach jeder Änderung in die Datei speichern
    private void saveToFile() throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(tasks);
        }
    }
}
