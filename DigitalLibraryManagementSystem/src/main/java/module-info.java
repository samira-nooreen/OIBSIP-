module com.online.exam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports com.online.lib;
    opens com.online.lib to javafx.fxml;
}