package control;

import model.Reserva;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clase responsable de guardar las reservas en un archivo de texto.
 *
 * Cada reserva se serializa en una línea utilizando el siguiente formato:
 * <pre>
 * ID_RESERVA;NUM_HABITACION;ID_HUESPED;FECHA_INICIO;FECHA_FIN;DIAS;CONFIRMADA
 * </pre>
 *
 * Las fechas se formatean con el patrón <strong>dd.MM.yyyy</strong>.
 *
 * @author
 * @version 1.0
 */
public class DataSaver {

    /**
     * Guarda una lista de reservas en un archivo de texto especificado.
     *
     * @param reservas Lista de objetos {@link Reserva} que se desean guardar.
     * @param ruta Ruta del archivo donde se escribirán las reservas.
     */
    public static void guardarReservas(List<Reserva> reservas, String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            for (Reserva r : reservas) {
                writer.write(r.getId() + ";" +
                        r.getHabitacion().getNumero() + ";" +
                        r.getHuesped().getId() + ";" +
                        r.getFechaInicio().format(fmt) + ";" +
                        r.getFechaFin().format(fmt) + ";" +
                        r.getDias() + ";" +
                        r.isEsConfirmada());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("❌ Error al guardar reservas: " + e.getMessage());
        }
    }
}