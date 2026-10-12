module br.marcus.ex {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.marcus.ex to javafx.fxml;
    exports br.marcus.ex;
}