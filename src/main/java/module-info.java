module org.otp2.otp2week3classassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.otp2.otp2week3classassignment to javafx.fxml;
    exports org.otp2.otp2week3classassignment;
}