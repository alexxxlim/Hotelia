package control;

import model.*;
import view.LoginView;

import javax.swing.*;
import java.util.List;

public class LoginController {
    private final List<Usuario> usuarios;
    private final LoginView view;

    public LoginController(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.view = new LoginView();
        init();
    }

    private void init() {
        view.getLoginButton().addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String correo = view.getEmail();
        String contra = view.getPassword();

        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo) && u.getContrasenia().equals(contra)) {
                JOptionPane.showMessageDialog(view,
                        "✅ Bienvenido, " + u.getNombre(),
                        "Acceso permitido",
                        JOptionPane.INFORMATION_MESSAGE);

                view.dispose();

                if (u instanceof Huesped huesped) {
                    new HuespedController(huesped);
                } else if (u instanceof Gerente gerente) {
                    new GerenteController(gerente);
                } else if (u instanceof Recepcionista recepcionista) {
                    new RecepcionistaController(recepcionista);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "⚠️ Rol no reconocido.",
                            "Error de rol",
                            JOptionPane.WARNING_MESSAGE);
                }
                return;
            }
        }

        // ❌ Error visible и на испанском
        JOptionPane.showMessageDialog(view,
                "❌ Datos incorrectos",
                "Error de autenticación",
                JOptionPane.ERROR_MESSAGE);
    }
}