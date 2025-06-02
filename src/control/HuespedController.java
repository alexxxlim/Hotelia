package control;

import model.*;
import view.HuespedView;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador principal para usuarios con rol de {@link Huesped}.
 * <p>
 * Esta clase implementa la lógica de negocio y conexión entre el modelo del huésped,
 * la vista gráfica ({@link HuespedView}) y las fuentes de datos del sistema.
 * <p>
 * Funcionalidades principales:
 * <ul>
 *   <li>Visualización de habitaciones disponibles.</li>
 *   <li>Creación y confirmación de reservas.</li>
 *   <li>Visualización de historial de reservas con opción de evaluación.</li>
 *   <li>Sincronización de datos desde/ hacia archivo externo.</li>
 * </ul>
 *
 * @author
 * @version 1.0
 */
public class HuespedController {

    private final Huesped modelo;
    private final HuespedView vista;

    /**
     * Inicializa el controlador con un modelo de {@link Huesped} autenticado
     * y construye su interfaz gráfica asociada.
     *
     * @param huesped El objeto de modelo que representa al huésped.
     */
    public HuespedController(Huesped huesped) {
        this.modelo = huesped;
        this.vista = new HuespedView(huesped.getNombre());
        init();
    }

    /**
     * Asocia los eventos de la interfaz gráfica con las acciones correspondientes
     * de este controlador.
     */
    private void init() {
        vista.actualizarBtn.addActionListener(e -> actualizarDatos());
        vista.verHabitacionesBtn.addActionListener(e -> verHabitaciones());
        vista.hacerReservaBtn.addActionListener(e -> hacerReserva());
        vista.historialBtn.addActionListener(e -> verHistorial());
        vista.salirBtn.addActionListener(e -> {
            vista.dispose();
            System.exit(0);
        });
    }

    /**
     * Actualiza y sincroniza los datos del sistema con el contenido del archivo externo de reservas.
     *
     * <p>Primero guarda las reservas actuales en un archivo de texto,
     * luego limpia la información actual de reservas en memoria
     * y finalmente recarga los datos desde ese archivo.</p>
     */
    private void actualizarDatos() {
        try {
            List<Huesped> huespedes = MainGUI.getUsuarios().stream()
                    .filter(u -> u instanceof Huesped)
                    .map(u -> (Huesped) u)
                    .collect(Collectors.toList());

            DataSaver.guardarReservas(MainGUI.getAllReservas(), "data/reservas.txt");

            MainGUI.getHabitaciones().forEach(h -> h.getReservas().clear());
            huespedes.forEach(h -> h.getReservas().clear());

            DataLoader.cargarReservas("data/reservas.txt", MainGUI.getHabitaciones(), huespedes);

            JOptionPane.showMessageDialog(vista, "🔄 Datos actualizados y sincronizados correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vista, "❌ Error al actualizar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra en un cuadro de diálogo todas las habitaciones disponibles actualmente,
     * indicando su tipo, precio y disponibilidad del día.
     */
    private void verHabitaciones() {
        StringBuilder sb = new StringBuilder("📅 Habitaciones disponibles hoy:\n");
        List<Habitacion> todas = MainGUI.getHabitaciones();
        for (Habitacion h : todas) {
            sb.append("Hab. ").append(h.getNumero())
                    .append(" - ").append(h.getTipo())
                    .append(" - €").append(h.getPrecio())
                    .append(" - Disponible: ").append(h.isDisponibleHoy() ? "✅ Sí" : "❌ No")
                    .append("\n");
        }
        JOptionPane.showMessageDialog(vista, sb.toString());
    }

    /**
     * Permite al huésped seleccionar una habitación y un rango de fechas para realizar una reserva.
     *
     * <p>El proceso incluye validaciones como:
     * <ul>
     *   <li>No permitir fechas en el pasado.</li>
     *   <li>Validar que la fecha de fin sea posterior a la de inicio.</li>
     *   <li>Verificar la disponibilidad real de la habitación.</li>
     * </ul>
     * <p>
     * En caso exitoso, guarda la reserva y muestra una confirmación con el total a pagar.
     * </p>
     */
    private void hacerReserva() {
        try {
            List<Habitacion> habitaciones = MainGUI.getHabitaciones();

            if (habitaciones.isEmpty()) {
                throw new IllegalStateException("No hay habitaciones cargadas.");
            }

            String[] opciones = habitaciones.stream()
                    .map(h -> "Hab " + h.getNumero() + " - " + h.getTipo() + " - €" + h.getPrecio())
                    .toArray(String[]::new);

            JComboBox<String> combo = new JComboBox<>(opciones);
            int seleccion = JOptionPane.showConfirmDialog(vista, combo, "Selecciona una habitación",
                    JOptionPane.OK_CANCEL_OPTION);
            if (seleccion != JOptionPane.OK_OPTION) return;

            Habitacion hab = habitaciones.get(combo.getSelectedIndex());

            JDatePickerImpl pickerInicio = DatePickerFactory.createPicker("Inicio");
            JDatePickerImpl pickerFin = DatePickerFactory.createPicker("Fin");

            int resIni = JOptionPane.showConfirmDialog(vista, pickerInicio, "Fecha de inicio", JOptionPane.OK_CANCEL_OPTION);
            if (resIni != JOptionPane.OK_OPTION) return;

            int resFin = JOptionPane.showConfirmDialog(vista, pickerFin, "Fecha de fin", JOptionPane.OK_CANCEL_OPTION);
            if (resFin != JOptionPane.OK_OPTION) return;

            LocalDate ini = LocalDate.of(
                    pickerInicio.getModel().getYear(),
                    pickerInicio.getModel().getMonth() + 1,
                    pickerInicio.getModel().getDay()
            );

            LocalDate fin = LocalDate.of(
                    pickerFin.getModel().getYear(),
                    pickerFin.getModel().getMonth() + 1,
                    pickerFin.getModel().getDay()
            );

            if (ini.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("No se puede reservar en el pasado.");
            }

            if (!fin.isAfter(ini)) {
                throw new IllegalArgumentException("La fecha final debe ser posterior a la inicial.");
            }

            if (!hab.estaDisponible(ini, fin)) {
                throw new IllegalStateException("La habitación no está disponible en esas fechas.");
            }

            int dias = (int) (fin.toEpochDay() - ini.toEpochDay());
            int nuevoId = MainGUI.generarReservaId();
            Reserva r = new Reserva(nuevoId, ini, fin, dias, false, modelo, hab);
            r.confirmarReserva();

            DataSaver.guardarReservas(MainGUI.getAllReservas(), "data/reservas.txt");

            double total = r.calcularPrecioTotal();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            JOptionPane.showMessageDialog(vista,
                    "✅ Reserva realizada con éxito.\n" +
                            "Hab: " + hab.getNumero() + "\n" +
                            "Tipo: " + hab.getTipo() + "\n" +
                            "Del: " + ini.format(formatter) + " al " + fin.format(formatter) + "\n" +
                            "Noches: " + dias + "\n" +
                            "Total: €" + String.format("%.2f", total),
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException | IllegalStateException e) {
            JOptionPane.showMessageDialog(vista, "❌ Error: " + e.getMessage(), "Error de reserva", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vista, "❌ Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra un historial de reservas realizadas por el huésped, permitiendo evaluarlas.
     *
     * <p>Se presenta una lista de reservas y, tras seleccionar una,
     * el huésped puede puntuar diferentes aspectos mediante estrellas.</p>
     */
    private void verHistorial() {
        List<Reserva> reservas = modelo.getReservas();
        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "📭 No hay reservas registradas.");
            return;
        }

        String[] opciones = reservas.stream().map(r -> {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return "Reserva #" + r.getId() + " | Hab " + r.getHabitacion().getNumero() +
                    " | " + r.getFechaInicio().format(fmt) + " - " + r.getFechaFin().format(fmt);
        }).toArray(String[]::new);

        JComboBox<String> lista = new JComboBox<>(opciones);
        int sel = JOptionPane.showConfirmDialog(vista, lista, "🕓 Tus reservas", JOptionPane.OK_CANCEL_OPTION);
        if (sel != JOptionPane.OK_OPTION) return;

        Reserva seleccionada = reservas.get(lista.getSelectedIndex());

        String[] criterios = {
                "🖥 Uso de la App",
                "🧼 Limpieza",
                "🛏 Comodidad",
                "🙋 Atención",
                "💰 Calidad-precio",
                "🌟 Experiencia general"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JComboBox<String>[] combos = new JComboBox[criterios.length];
        for (int i = 0; i < criterios.length; i++) {
            JPanel fila = new JPanel();
            fila.setLayout(new BoxLayout(fila, BoxLayout.X_AXIS));
            fila.add(new JLabel(criterios[i] + ": "));
            combos[i] = new JComboBox<>(new String[]{"⭐", "⭐⭐", "⭐⭐⭐", "⭐⭐⭐⭐", "⭐⭐⭐⭐⭐"});
            fila.add(Box.createHorizontalStrut(10));
            fila.add(combos[i]);
            panel.add(fila);
            panel.add(Box.createVerticalStrut(5));
        }

        int res = JOptionPane.showConfirmDialog(vista, panel, "📝 Evalúa tu reserva", JOptionPane.OK_CANCEL_OPTION);
        if (res != JOptionPane.OK_OPTION) return;

        StringBuilder resultado = new StringBuilder("Gracias por tu evaluación:\n\n");
        for (int i = 0; i < criterios.length; i++) {
            resultado.append(criterios[i]).append(": ")
                    .append(combos[i].getSelectedItem()).append("\n");
        }

        JOptionPane.showMessageDialog(vista, resultado.toString(), "✅ ¡Hecho!", JOptionPane.INFORMATION_MESSAGE);
    }
}