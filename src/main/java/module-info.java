module com.andrewn.java2308javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.andrewn.java2308javafx to javafx.fxml;
    exports com.andrewn.java2308javafx;
}