package control;

import java.time.LocalDate;
import java.util.List;

public class Habitacion {
    int numero;
    String tipo;
    double precio;
    boolean disponible;
    List<Reseña> reseñas;
    boolean mantenimiento;

    public Habitacion(){
    }
    public Habitacion(int numero, String tipo, double precio, boolean disponible, boolean mantenimiento, List<Reseñas> reseñas){
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
        this.mantenimiento = mantenimiento;
        this.reseñas = reseñas;
    }

    boolean estaDisponible(LocalDate ini, LocalDate fin){

    }

    double calcularPrecioConDescuento(boolean vip){

    }

    double ratingPromedio(){

    }
}
