import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                Formulario formulario = new Formulario();
                formulario.panel = new JPanel(); //Quando acionado ele limpa o valor da variável antiga, construindo um novo objeto
                formulario.panel.add(new JLabel("Personalizando o Panel"));
                formulario.add(formulario.panel);
                formulario.setVisible(true);
            }
        });
    }
}