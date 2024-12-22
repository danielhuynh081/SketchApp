module com.example.sketchpad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sketchpad to javafx.fxml;
    exports com.example.sketchpad;
}