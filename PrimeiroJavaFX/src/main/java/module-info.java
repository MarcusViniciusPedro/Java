module br.alunomarcus.primeirojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.alunomarcus.primeirojavafx to javafx.fxml;
    exports br.alunomarcus.primeirojavafx;
}