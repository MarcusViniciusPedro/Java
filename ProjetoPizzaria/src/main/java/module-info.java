module br.igor.pizzaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.igor.pizzaria to javafx.fxml;
    opens br.igor.pizzaria.controller to javafx.fxml;
    opens br.igor.pizzaria.model to javafx.fxml;
    exports br.igor.pizzaria;
}