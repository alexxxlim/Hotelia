package view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ventana principal de interfaz gráfica para el rol de {@link model.Huesped}.
 * <p>
 * Proporciona botones de acción para ver habitaciones, hacer reservas,
 * consultar historial, actualizar datos y salir del sistema.
 * También incluye un reloj en tiempo real y recursos gráficos como logotipo y fondo.
 * <p>
 * Esta vista se comunica con {@code HuespedController} para ejecutar las funcionalidades.
 *
 * @author
 * @version 1.0
 */
public class HuespedView extends JFrame {

    /**
     * Botón para actualizar los datos del sistema (reservas, habitaciones).
     */
    public final JButton actualizarBtn = new JButton("🔄 Actualizar");

    /**
     * Botón para ver habitaciones disponibles.
     */
    public final JButton verHabitacionesBtn = new JButton("🛏 Ver habitaciones");

    /**
     * Botón para hacer una nueva reserva.
     */
    public final JButton hacerReservaBtn = new JButton("📅 Hacer reserva");

    /**
     * Botón para ver historial de reservas.
     */
    public final JButton historialBtn = new JButton("📜 Ver historial");

    /**
     * Botón para salir de la sesión.
     */
    public final JButton salirBtn = new JButton("🚪 Salir");

    /**
     * Etiqueta que muestra la fecha y hora actual.
     */
    private final JLabel relojLabel = new JLabel();

    /**
     * Constructor de la ventana del huésped. Inicializa todos los elementos gráficos.
     *
     * @param nombreUsuario Nombre del huésped autenticado (para saludo personalizado).
     */
    public HuespedView(String nombreUsuario) {
        setTitle("Hotelia - Huesped");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel fondo = new JLabel();
        fondo.setLayout(new BorderLayout());

        try {
            ImageIcon fondoImg = new ImageIcon("data/fondo.png");
            Image fondoEscalado = fondoImg.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            fondo.setIcon(new ImageIcon(fondoEscalado));
        } catch (Exception e) {
            fondo.setText("FONDO NO ENCONTRADO");
        }

        JLabel logo = new JLabel();
        try {
            ImageIcon icon = new ImageIcon("data/logo.png");
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            logo.setText("🏨 HOTELIA");
            logo.setFont(new Font("Arial", Font.BOLD, 22));
        }
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel saludo = new JLabel("👤 Bienvenido, " + nombreUsuario);
        saludo.setFont(new Font("Arial", Font.PLAIN, 16));

        relojLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        relojLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        topBar.add(saludo, BorderLayout.WEST);
        topBar.add(relojLabel, BorderLayout.EAST);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setOpaque(false);
        northPanel.add(logo, BorderLayout.NORTH);
        northPanel.add(topBar, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 15, 15));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        JButton[] botones = {
                actualizarBtn,
                verHabitacionesBtn,
                hacerReservaBtn,
                historialBtn,
                salirBtn
        };
        for (JButton b : botones) {
            b.setFont(new Font("Arial", Font.PLAIN, 18));
            centerPanel.add(b);
        }

        fondo.add(northPanel, BorderLayout.NORTH);
        fondo.add(centerPanel, BorderLayout.CENTER);
        add(fondo);

        iniciarReloj();
        setVisible(true);
    }

    /**
     * Inicia un temporizador que actualiza continuamente la etiqueta del reloj con la hora y fecha actual.
     */
    private void iniciarReloj() {
        Timer timer = new Timer(1000, e -> {
            String fecha = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
            relojLabel.setText("📅 " + fecha);
        });
        timer.start();
    }
}