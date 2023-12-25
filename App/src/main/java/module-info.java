module jma.app {
    requires javafx.controls;
    requires javafx.fxml;


    opens jma.app to javafx.fxml;
    exports jma.app;
}