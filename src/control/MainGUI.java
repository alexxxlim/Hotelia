package control;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class MainGUI {

    // 📦 Глобальные данные
    private static final List<Habitacion> habitaciones = new ArrayList<>();
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static int nextReservaId = 1;

    public static void main(String[] args) {
        // 🛏️ Habitaciones iniciales
        habitaciones.add(new Habitacion(1, "Estándar", 50.0, true, new ArrayList<>(), new ArrayList<>()));
        habitaciones.add(new Habitacion(2, "VIP", 120.0, true, new ArrayList<>(), new ArrayList<>()));
        habitaciones.add(new Habitacion(3, "Suite", 200.0, true, new ArrayList<>(), new ArrayList<>()));

        // 👤 Usuarios iniciales
        usuarios.add(new Huesped(1, 101, "Luis", "luis@mail.com", "1234", false));
        usuarios.add(new Gerente(2, "Ana", "ana@hotel.com", "admin"));
        usuarios.add(new Recepcionista(3, "Pedro", "pedro@hotel.com", "rec"));

        // 🚀 Iniciar login
        new LoginController(usuarios);
    }

    // 🔄 Obtener habitaciones
    public static List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    // 🔄 Obtener usuarios
    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    // 🔄 Obtener todas las reservas (de todas las habitaciones)
    public static List<Reserva> getAllReservas() {
        List<Reserva> todas = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            todas.addAll(h.getReservas());
        }
        return todas;
    }

    // 🆔 Generar ID único para reserva
    public static int generarReservaId() {
        return nextReservaId++;
    }
}