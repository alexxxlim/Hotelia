package model;

/**
 * Clase que representa una reseña escrita por un huésped sobre una habitación.
 *
 * Una reseña incluye un comentario, una puntuación numérica del 1 al 5
 * y el autor (usuario que la ha realizado).
 *
 * Esta clase permite validar el contenido de la reseña y generar un resumen textual.
 *
 * @author
 * @version 1.0
 */
public class Resenia {

    private String comentario;
    private int rating;
    private Huesped autor;

    /**
     * Constructor principal de reseña.
     *
     * @param comment Texto del comentario.
     * @param rating Valoración numérica (entre 1 y 5).
     * @param autor Objeto {@link Huesped} que realizó la reseña.
     */
    public Resenia(String comment, int rating, Huesped autor) {
        this.comentario = comment;
        this.rating = rating;
        this.autor = autor;
    }

    // ========================
    // Getters
    // ========================

    /**
     * Obtiene el autor de la reseña.
     *
     * @return Objeto {@link Huesped} que escribió la reseña.
     */
    public Huesped getAutor() {
        return autor;
    }

    /**
     * Obtiene la puntuación de la reseña.
     *
     * @return Entero entre 1 y 5.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Obtiene el texto del comentario.
     *
     * @return Comentario en formato {@link String}.
     */
    public String getComentario() {
        return comentario;
    }

    // ========================
    // Setters
    // ========================

    /**
     * Establece el autor de la reseña.
     *
     * @param autor Objeto {@link Huesped}.
     */
    public void setAutor(Huesped autor) {
        this.autor = autor;
    }

    /**
     * Establece el comentario de la reseña.
     *
     * @param comentario Texto descriptivo de la reseña.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Establece la puntuación de la reseña.
     *
     * @param rating Valoración entre 1 y 5.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    // ========================
    // Funcionalidad
    // ========================

    /**
     * Verifica si la reseña es válida.
     * Una reseña válida debe tener un comentario no vacío y una puntuación entre 1 y 5.
     *
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    public boolean esValida() {
        return rating >= 1 && rating <= 5 && comentario != null && !comentario.isBlank();
    }

    /**
     * Devuelve un resumen formateado de la reseña.
     *
     * @return Cadena con formato "[rating/5] comentario - autor".
     */
    public String resumen() {
        return "[" + rating + "/5] " + comentario + " - " + autor.getNombre();
    }
}