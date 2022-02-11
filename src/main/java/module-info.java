module com.yes.yes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.yes.yes to javafx.fxml;
    exports com.yes.yes;
}