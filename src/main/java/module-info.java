module cookbook.digitalcookbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens cookbook.javafx to javafx.fxml;
    exports cookbook.javafx;
}