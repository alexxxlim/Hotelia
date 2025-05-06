package model;

public class Resenia {
    public String comentario;
    public int rating;
    public Huesped autor;

    public Resenia(String comment, int rating, Huesped autor) {
        this.comentario = comment;
        this.rating = rating;
        this.autor = autor;
    }

    // Funciones Principales:

    public boolean esValida() {
        return rating >= 1 && rating <= 5 && comentario != null && !comentario.isBlank();
    }

    public String resumen() {
        return "[" + rating + "/5] " + comentario + " - " + autor.nombre;
    }
}