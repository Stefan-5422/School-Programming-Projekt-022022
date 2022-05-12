module com.yes.yes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.yes.yes to javafx.fxml;
    exports com.yes.yes;
    exports com.yes.yes.utils;
    opens com.yes.yes.utils to javafx.fxml;
}