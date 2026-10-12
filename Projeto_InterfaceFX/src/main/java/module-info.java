module br.alunomarcus.projeto_interfacefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.alunomarcus.projeto_interfacefx to javafx.fxml;
    opens br.alunomarcus.projeto_interfacefx.Controllers to javafx.fxml;
    exports br.alunomarcus.projeto_interfacefx;
}