package control;

import model.*;
import view.LoginView;

import javax.swing.*;
import java.util.List;

/**
 * Controlador responsable de gestionar el inicio de sesión de usuarios en la aplicación.
 * <p>
 * Este controlador se conecta con la vista {@link LoginView} para recibir las credenciales
 * ingresadas por el usuario y verificar si coinciden con algún objeto {@link Usuario} válido.
 * <p>
 * Según el tipo de usuario autenticado, lanza el controlador correspondiente:
 * <ul>
 *   <li>{@link HuespedController} para huéspedes</li>
 *   <li>{@link GerenteController} para gerentes</li>
 *   <li>{@link RecepcionistaController} para recepcionistas</li>
 * </ul>
 * <p>
 * Si las credenciales no son válidas o el tipo de rol no es reconocido, muestra un mensaje de error.
 *
 * @author
 * @version 1.0
 */
public class LoginController {

    private final List<Usuario> usuarios;
    private final LoginView view;

    /**
     * Constructor del controlador de login.
     *
     * @param usuarios Lista de usuarios registrados en el sistema.
     */
    public LoginController(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.view = new LoginView();
        init();
    }

    /**
     * Inicializa los eventos de la vista, en particular el botón de acceso.
     */
    private void init() {
        view.getLoginButton().addActionListener(e -> autenticar());
    }

    /**
     * Verifica las credenciales introducidas por el usuario.
     * <p>
     * Si las credenciales coinciden con un usuario existente, se abre la interfaz
     * correspondiente según el rol. Si no coinciden, se muestra un mensaje de error.
     */
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

        JOptionPane.showMessageDialog(view,
                "❌ Datos incorrectos",
                "Error de autenticación",
                JOptionPane.ERROR_MESSAGE);
    }
}