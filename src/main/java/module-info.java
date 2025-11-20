module trinque.eric.fractalGenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires atlantafx.base;

    opens trinque.eric.fractalGenerator to javafx.fxml;
    opens trinque.eric.fractalGenerator.app to javafx.fxml;
    exports trinque.eric.fractalGenerator.app;

}