module ps.frontend.desktop {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens ps.frontend.desktop to javafx.fxml, com.google.gson;
    opens ps.frontend.desktop.controllers;
    opens ps.frontend.desktop.models;
    exports ps.frontend.desktop;
    exports ps.frontend.desktop.controllers;
}
