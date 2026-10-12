import javax.swing.*;
import java.awt.*;

public class Formulario extends JFrame {
    public JPanel panel;

    public Formulario() {
        setTitle("Formulario");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Nome:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Email:"));
        panel.add(new JTextField("@email.com",20));
        panel.add(new JButton("Enviar"));
        add(panel);
    }
}
