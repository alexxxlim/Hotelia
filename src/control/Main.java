package control;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que simula un sistema de gestión hotelera a través de consola.
 * Permite el inicio de sesión de usuarios y muestra menús específicos según el rol.
 * <p>
 * Contiene métodos para inicializar datos, autenticar usuarios, gestionar menús
 * de huésped, gerente y recepcionista, así como registrar nuevas reservas.
 *
 * @author Aleksei Limin
 * GitHub: alexxxlim
 * @author Lucas Henrique Rangel Resende
 * @author Gabriel Jiménez Bustos
 * //---------------------------------------------
 * @author Miguel Márquez Martínez
 * @author Ricardo Recio Villodre
 * //---------------------------------------------
 * @version 1.0
 */
public class Main {

    static ArrayList<Habitacion> habitaciones = new ArrayList<>();
    static ArrayList<Huesped> huespedes = new ArrayList<>();
    static ArrayList<Gerente> gerentes = new ArrayList<>();
    static ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int idReserva = 1;

    /**
     * Método principal que inicia el sistema.
     * Inicializa los datos, solicita autenticación
     * y dirige al usuario al menú correspondiente según su rol.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        inicializarSistema();
        Usuario usuario = autenticarUsuario();
        if (usuario == null) return;

        if (usuario instanceof Huesped huesped) menuHuesped(huesped);
        else if (usuario instanceof Gerente gerente) menuGerente(gerente);
        else if (usuario instanceof Recepcionista recepcionista) menuRecepcionista(recepcionista);
        else System.out.println("Rol desconocido.");
    }

    /**
     * Inicializa el sistema con habitaciones y usuarios de ejemplo.
     */
    static void inicializarSistema() {
        habitaciones.add(new Habitacion(1, "Estándar", 50.0, true, new ArrayList<>(), new ArrayList<>()));
        habitaciones.add(new Habitacion(2, "VIP", 100.0, true, new ArrayList<>(), new ArrayList<>()));
        huespedes.add(new Huesped(1, 101, "Luis", "luis@mail.com", "1234", false));
        gerentes.add(new Gerente(2, "Ana", "ana@hotel.com", "admin"));
        recepcionistas.add(new Recepcionista(3, "Pedro", "pedro@hotel.com", "rec"));
    }

    /**
     * Solicita al usuario su correo y contraseña y verifica sus credenciales.
     *
     * @return Un objeto {@link Usuario} válido si se autentica correctamente,
     * o null si las credenciales son incorrectas.
     */
    static Usuario autenticarUsuario() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String contra = sc.nextLine();

        for (var h : huespedes) if (h.getCorreo().equals(correo) && h.getContrasenia().equals(contra)) return h;
        for (var g : gerentes) if (g.getCorreo().equals(correo) && g.getContrasenia().equals(contra)) return g;
        for (var r : recepcionistas) if (r.getCorreo().equals(correo) && r.getContrasenia().equals(contra)) return r;

        System.out.println("Credenciales incorrectas.");
        return null;
    }

    /**
     * Muestra el menú interactivo para un usuario con rol de {@link Huesped}.
     *
     * @param h Objeto de tipo {@link Huesped} autenticado.
     */
    static void menuHuesped(Huesped h) {
        while (true) {
            System.out.println("\n--- MENÚ HUÉSPED ---");
            System.out.println("1. Ver habitaciones");
            System.out.println("2. Hacer reserva");
            System.out.println("3. Ver historial");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> habitaciones.forEach(hab ->
                        System.out.printf("Hab. %d - %s - %.2f - Disponible: %b\n", hab.getNumero(), hab.getTipo(), hab.getPrecio(), hab.isEsDisponible()));
                case 2 -> hacerReserva(h);
                case 3 -> h.getReservas().forEach(r ->
                        System.out.printf("Reserva %d - Hab. %d - %s a %s\n", r.getId(), r.getHabitacion().getNumero(), r.getFechaInicio(), r.getFechaFin()));
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    /**
     * Muestra el menú interactivo para un usuario con rol de {@link Gerente}.
     *
     * @param g Objeto de tipo {@link Gerente} autenticado.
     */
    static void menuGerente(Gerente g) {
        while (true) {
            System.out.println("\n--- MENÚ GERENTE ---");
            System.out.println("1. Ver habitaciones");
            System.out.println("2. Agregar habitación");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> habitaciones.forEach(h ->
                        System.out.printf("Hab. %d - %s - %.2f\n", h.getNumero(), h.getTipo(), h.getPrecio()));
                case 2 -> {
                    System.out.print("Número: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    habitaciones.add(new Habitacion(num, tipo, precio, true, new ArrayList<>(), new ArrayList<>()));
                    System.out.println("Habitación agregada.");
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    /**
     * Muestra el menú interactivo para un usuario con rol de {@link Recepcionista}.
     *
     * @param r Objeto de tipo {@link Recepcionista} autenticado.
     */
    static void menuRecepcionista(Recepcionista r) {
        while (true) {
            System.out.println("\n--- MENÚ RECEPCIONISTA ---");
            System.out.println("1. Ver reservas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> reservas.forEach(res ->
                        System.out.printf("Reserva %d - %s - Hab. %d\n", res.getId(), res.getHuesped().getNombre(), res.getHabitacion().getNumero()));
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    /**
     * Permite a un {@link Huesped} realizar una nueva reserva si la habitación está disponible.
     *
     * @param h Objeto de tipo {@link Huesped} que realiza la reserva.
     */
    static void hacerReserva(Huesped h) {
        System.out.print("Número de habitación: ");
        int num = sc.nextInt();
        sc.nextLine();
        Habitacion hab = habitaciones.stream().filter(hh -> hh.getNumero() == num).findFirst().orElse(null);
        if (hab == null || !hab.isEsDisponible()) {
            System.out.println("Habitación no disponible.");
            return;
        }
        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        LocalDate ini = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(sc.nextLine());

        Reserva r = new Reserva(idReserva++, ini, fin, (int) (fin.toEpochDay() - ini.toEpochDay()), false, h, hab);
        try {
            r.confirmarReserva();
            reservas.add(r);
            System.out.println("Reserva confirmada.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}