module techaholic.recruited {
    requires javafx.controls;
    requires javafx.fxml;

    opens techaholic.recruited to javafx.fxml;
    exports techaholic.recruited;
}
