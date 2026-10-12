module br.marcus.trabalhofinal_sistemareservas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.marcus.trabalhofinal_sistemareservas to javafx.fxml;
    exports br.marcus.trabalhofinal_sistemareservas;
    exports br.marcus.trabalhofinal_sistemareservas.model;
    opens br.marcus.trabalhofinal_sistemareservas.model to javafx.fxml;
    exports br.marcus.trabalhofinal_sistemareservas.util;
    opens br.marcus.trabalhofinal_sistemareservas.util to javafx.fxml;
    exports br.marcus.trabalhofinal_sistemareservas.controller;
    opens br.marcus.trabalhofinal_sistemareservas.controller to javafx.fxml;

}