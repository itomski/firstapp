module de.lubowiecki.gui.firstapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.lubowiecki.gui.firstapp to javafx.fxml;
    exports de.lubowiecki.gui.firstapp;
}