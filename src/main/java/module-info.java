module com.example.assignment1java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.assignment1java to javafx.fxml;
    exports com.example.assignment1java;
}