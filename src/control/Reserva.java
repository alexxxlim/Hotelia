package control;

import java.time.LocalDate;

public class Reserva {
    LocalDate fechaInicio;
    LocalDate fechaFin;
    boolean confirmada;
    Huesped huesped;
    Habitacion habitacion;

    public Reserva(){

    }
    public Reserva(LocalDate fechaIni, LocalDate fechaFin, boolean confirmada, Huesped huesped, Habitacion habitacion){
        this.fechaInicio = fechaIni;
        this.fechaFin = fechaFin;
        this.confirmada = confirmada;
        this.huesped = huesped;
        this.habitacion = habitacion;
    }

    double calcularPrecio(){

    }

    void confirmarReserva(){

    }
    void cancelarReserva(){

    }
}
