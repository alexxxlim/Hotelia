package control;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clase encargada de cargar reservas desde un archivo de texto.
 * <p>
 * Cada línea del archivo representa una reserva, cuyos datos se parsean
 * y se integran tanto en la habitación como en el huésped correspondiente.
 * <p>
 * El formato esperado de las líneas es:
 * <pre>
 * ID_RESERVA;NUM_HABITACION;ID_HUESPED;FECHA_INICIO;FECHA_FIN;DIAS;CONFIRMADA
 * </pre>
 * <p>
 * Las fechas deben tener el formato <strong>dd.MM.yyyy</strong>.
 *
 * @author
 * @version 1.0
 */
public class DataLoader {

    /**
     * Carga reservas desde un archivo especificado y las asigna a las habitaciones
     * y huéspedes correspondientes si existen.
     *
     * @param path         Ruta del archivo de texto que contiene las reservas.
     * @param habitaciones Lista de habitaciones disponibles en el sistema.
     * @param huespedes    Lista de huéspedes registrados.
     */
    public static void cargarReservas(String path, List<Habitacion> habitaciones, List<Huesped> huespedes) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(";");
                int id = Integer.parseInt(p[0]);
                int numHab = Integer.parseInt(p[1]);
                int idHuesped = Integer.parseInt(p[2]);
                LocalDate inicio = LocalDate.parse(p[3], fmt);
                LocalDate fin = LocalDate.parse(p[4], fmt);
                int dias = Integer.parseInt(p[5]);
                boolean confirmada = Boolean.parseBoolean(p[6]);

                Habitacion hab = habitaciones.stream()
                        .filter(h -> h.getNumero() == numHab)
                        .findFirst()
                        .orElse(null);

                Huesped huesped = huespedes.stream()
                        .filter(h -> h.getId() == idHuesped)
                        .findFirst()
                        .orElse(null);

                if (hab != null && huesped != null) {
                    Reserva r = new Reserva(id, inicio, fin, dias, confirmada, huesped, hab);
                    hab.getReservas().add(r);
                    huesped.getReservas().add(r);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error al cargar reservas: " + e.getMessage());
        }
    }
}