package control;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataLoader {

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

                Habitacion hab = habitaciones.stream().filter(h -> h.numero == numHab).findFirst().orElse(null);
                Huesped huesped = huespedes.stream().filter(h -> h.getId() == idHuesped).findFirst().orElse(null);

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