module jma.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens jma.app to javafx.fxml;
    exports jma.app;
}