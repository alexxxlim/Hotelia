package control;

import model.Reserva;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataSaver {
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