package de.lubowiecki.gui.firstapp;

import javafx.fxml.FXML;

import java.io.IOException;

public class NextController {

    // In eine andere View wechseln
    @FXML
    protected void changeView() throws IOException {
        App.setRoot("main-view"); // Wechsel der View
    }
}
