package view;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana gráfica de inicio de sesión para la aplicación Hotelia.
 *
 * Esta clase proporciona un formulario simple con campos para ingresar correo
 * y contraseña, junto con un botón de inicio de sesión.
 * También muestra un logotipo o, en su defecto, una etiqueta de texto con el nombre del sistema.
 *
 * Se utiliza en conjunto con el controlador {@link control.LoginController}.
 *
 * Componentes principales:
 * <ul>
 *   <li>Campo de texto para correo electrónico.</li>
 *   <li>Campo de contraseña oculto.</li>
 *   <li>Botón para iniciar sesión.</li>
 * </ul>
 *
 * @author
 * @version 1.0
 */
public class LoginView extends JFrame {

    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("🔐 Iniciar sesión");

    /**
     * Constructor que configura y muestra la ventana de login.
     */
    public LoginView() {
        setTitle("🏨 Hotelia - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel logoLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon("data/logo.png");
            Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            logoLabel.setText("🏨 HOTELIA");
            logoLabel.setFont(new Font("Arial", Font.BOLD, 22));
        }
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.add(new JLabel("📧 Correo:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("🔑 Contraseña:"));
        formPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(loginButton, BorderLayout.CENTER);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Devuelve el texto ingresado en el campo de correo.
     *
     * @return Correo electrónico introducido por el usuario.
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * Devuelve la contraseña introducida por el usuario como texto plano.
     *
     * @return Contraseña en formato {@link String}.
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Devuelve el botón de inicio de sesión para añadirle listeners externos.
     *
     * @return Objeto {@link JButton} de login.
     */
    public JButton getLoginButton() {
        return loginButton;
    }
}