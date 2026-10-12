module br.marcus.mercadinho {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.marcus.mercadinho to javafx.fxml;
    exports br.marcus.mercadinho;
    exports br.marcus.mercadinho.model;
    opens br.marcus.mercadinho.model to javafx.fxml;
    exports br.marcus.mercadinho.controller;
    opens br.marcus.mercadinho.controller to javafx.fxml;
    exports br.marcus.mercadinho.util;
    opens br.marcus.mercadinho.util to javafx.fxml;
}