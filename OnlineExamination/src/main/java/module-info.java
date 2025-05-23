module com.online.exam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.online.exam to javafx.fxml;
    exports com.online.exam;
}