module com.example.tetrisfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.tetrisfx to javafx.fxml;
    exports com.example.tetrisfx;
}