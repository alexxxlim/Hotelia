package control;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Habitacion> habitaciones = new ArrayList<>();
    static ArrayList<Huesped> huespedes = new ArrayList<>();
    static ArrayList<Gerente> gerentes = new ArrayList<>();
    static ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int idReserva = 1;

    public static void main(String[] args) {
        inicializarSistema();
        Usuario usuario = autenticarUsuario();
        if (usuario == null) return;

        if (usuario instanceof Huesped huesped) menuHuesped(huesped);
        else if (usuario instanceof Gerente gerente) menuGerente(gerente);
        else if (usuario instanceof Recepcionista recepcionista) menuRecepcionista(recepcionista);
        else System.out.println("Rol desconocido.");
    }

    static void inicializarSistema() {
        habitaciones.add(new Habitacion(1, "Estándar", 50.0, true, new ArrayList<>(), new ArrayList<>()));
        habitaciones.add(new Habitacion(2, "VIP", 100.0, true, new ArrayList<>(), new ArrayList<>()));
        huespedes.add(new Huesped(1, 101, "Luis", "luis@mail.com", "1234", false));
        gerentes.add(new Gerente(2, "Ana", "ana@hotel.com", "admin"));
        recepcionistas.add(new Recepcionista(3, "Pedro", "pedro@hotel.com", "rec"));
    }

    static Usuario autenticarUsuario() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String contra = sc.nextLine();

        for (var h : huespedes) if (h.correo.equals(correo) && h.contrasenia.equals(contra)) return h;
        for (var g : gerentes) if (g.correo.equals(correo) && g.contrasenia.equals(contra)) return g;
        for (var r : recepcionistas) if (r.correo.equals(correo) && r.contrasenia.equals(contra)) return r;

        System.out.println("Credenciales incorrectas.");
        return null;
    }

    static void menuHuesped(Huesped h) {
        while (true) {
            System.out.println("\n--- MENÚ HUÉSPED ---");
            System.out.println("1. Ver habitaciones");
            System.out.println("2. Hacer reserva");
            System.out.println("3. Ver historial");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1 -> habitaciones.forEach(hab ->
                        System.out.printf("Hab. %d - %s - %.2f - Disponible: %b\n", hab.numero, hab.tipo, hab.precio, hab.esDisponible));
                case 2 -> hacerReserva(h);
                case 3 -> h.reservas.forEach(r ->
                        System.out.printf("Reserva %d - Hab. %d - %s a %s\n", r.id, r.habitacion.numero, r.fechaInicio, r.fechaFin));
                case 0 -> { return; }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    static void menuGerente(Gerente g) {
        while (true) {
            System.out.println("\n--- MENÚ GERENTE ---");
            System.out.println("1. Ver habitaciones");
            System.out.println("2. Agregar habitación");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1 -> habitaciones.forEach(h ->
                        System.out.printf("Hab. %d - %s - %.2f\n", h.numero, h.tipo, h.precio));
                case 2 -> {
                    System.out.print("Número: "); int num = sc.nextInt(); sc.nextLine();
                    System.out.print("Tipo: "); String tipo = sc.nextLine();
                    System.out.print("Precio: "); double precio = sc.nextDouble(); sc.nextLine();
                    habitaciones.add(new Habitacion(num, tipo, precio, true, new ArrayList<>(), new ArrayList<>()));
                    System.out.println("Habitación agregada.");
                }
                case 0 -> { return; }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    static void menuRecepcionista(Recepcionista r) {
        while (true) {
            System.out.println("\n--- MENÚ RECEPCIONISTA ---");
            System.out.println("1. Ver reservas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt(); sc.nextLine();
            switch (op) {
                case 1 -> reservas.forEach(res ->
                        System.out.printf("Reserva %d - %s - Hab. %d\n", res.id, res.huesped.nombre, res.habitacion.numero));
                case 0 -> { return; }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    static void hacerReserva(Huesped h) {
        System.out.print("Número de habitación: ");
        int num = sc.nextInt(); sc.nextLine();
        Habitacion hab = habitaciones.stream().filter(hh -> hh.numero == num).findFirst().orElse(null);
        if (hab == null || !hab.esDisponible) {
            System.out.println("Habitación no disponible.");
            return;
        }
        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        LocalDate ini = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(sc.nextLine());

        Reserva r = new Reserva(idReserva++, ini, fin, (int)(fin.toEpochDay() - ini.toEpochDay()), false, h, hab);
        try {
            r.confirmarReserva();
            reservas.add(r);
            System.out.println("Reserva confirmada.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}