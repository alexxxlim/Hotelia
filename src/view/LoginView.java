package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("🔐 Iniciar sesión");

    public LoginView() {
        setTitle("🏨 Hotelia - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------- ЛОГОТИП ----------
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

        // ---------- ФОРМА ----------
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(new JLabel("📧 Correo:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("🔑 Contraseña:"));
        formPanel.add(passwordField);

        // ---------- КНОПКА ----------
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(loginButton, BorderLayout.CENTER);

        // ---------- СБОРКА ----------
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}