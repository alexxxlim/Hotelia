package control;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal de la aplicación que gestiona la inicialización de los datos
 * y el inicio del sistema de login para la plataforma Hotelia.
 * <p>
 * Esta clase contiene listas estáticas de habitaciones y usuarios,
 * así como un contador para generar identificadores únicos de reservas.
 *
 * @author Aleksei Limin
 * GitHub: alexxxlim
 * @author Lucas Henrique Rangel Resende
 * @author Gabriel Jiménez Bustos
 * //---------------------------------------------
 * @author Miguel Márquez Martínez
 * @author Ricardo Recio Villodre
 * //---------------------------------------------
 * @version 2.1
 */
public class MainGUI {

    /**
     * Lista global de habitaciones disponibles en el sistema.
     */
    private static final List<Habitacion> habitaciones = new ArrayList<>();

    /**
     * Lista global de usuarios registrados en el sistema.
     */
    private static final List<Usuario> usuarios = new ArrayList<>();

    /**
     * Contador para generar IDs únicos de reservas.
     */
    private static int nextReservaId = 1;

    /**
     * Método principal que inicia la aplicación.
     * <p>
     * Se encarga de cargar los datos iniciales (habitaciones y usuarios)
     * y de iniciar el controlador de login.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        habitaciones.add(new Habitacion(1, "Estándar", 50.0, true, new ArrayList<>(), new ArrayList<>()));
        habitaciones.add(new Habitacion(2, "VIP", 120.0, true, new ArrayList<>(), new ArrayList<>()));
        habitaciones.add(new Habitacion(3, "Suite", 200.0, true, new ArrayList<>(), new ArrayList<>()));

        usuarios.add(new Huesped(1, 101, "Luis", "luis@mail.com", "1234", false));
        usuarios.add(new Gerente(2, "Ana", "ana@hotel.com", "admin"));
        usuarios.add(new Recepcionista(3, "Pedro", "pedro@hotel.com", "rec"));

        new LoginController(usuarios);
    }

    /**
     * Devuelve la lista de habitaciones actuales.
     *
     * @return Lista de objetos {@link Habitacion}.
     */
    public static List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    /**
     * Devuelve la lista de usuarios registrados.
     *
     * @return Lista de objetos {@link Usuario}.
     */
    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Recupera todas las reservas registradas en todas las habitaciones.
     *
     * @return Lista de objetos {@link Reserva}.
     */
    public static List<Reserva> getAllReservas() {
        List<Reserva> todas = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            todas.addAll(h.getReservas());
        }
        return todas;
    }

    /**
     * Genera un identificador único para una nueva reserva.
     *
     * @return Un número entero que representa un ID único de reserva.
     */
    public static int generarReservaId() {
        return nextReservaId++;
    }
}