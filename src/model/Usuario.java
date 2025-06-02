package model;

/**
 * Clase base para representar a cualquier usuario del sistema Hotelia.
 *
 * Contiene información común como identificador, nombre, correo electrónico, contraseña
 * y estado de activación. También ofrece utilidades como detección del rol,
 * cambio de contraseña y desactivación de cuenta.
 *
 * Esta clase es heredada por {@link Huesped}, {@link Gerente} y {@link Recepcionista}.
 *
 * @author
 * @version 1.0
 */
public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String contrasenia;
    private boolean activo = true;

    /**
     * Constructor de la clase Usuario.
     *
     * @param id Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param correo Correo electrónico del usuario.
     * @param contrasenia Contraseña de acceso.
     */
    public Usuario(int id, String nombre, String correo, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    // ========================
    // Getters
    // ========================

    /**
     * Devuelve el ID del usuario.
     *
     * @return ID numérico.
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el nombre del usuario.
     *
     * @return Nombre completo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     *
     * @return Correo en formato String.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return Contraseña actual.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Indica si la cuenta del usuario está activa.
     *
     * @return {@code true} si está activa, {@code false} si está desactivada.
     */
    public boolean isActivo() {
        return activo;
    }

    // ========================
    // Setters
    // ========================

    /**
     * Establece un nuevo ID para el usuario.
     *
     * @param id Nuevo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece un nuevo nombre para el usuario.
     *
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece un nuevo correo electrónico.
     *
     * @param correo Nuevo correo.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Establece una nueva contraseña.
     *
     * @param contrasenia Nueva contraseña.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Activa o desactiva la cuenta del usuario.
     *
     * @param activo Estado deseado.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // ========================
    // Funcionalidad
    // ========================

    /**
     * Devuelve el nombre de la clase del usuario como su rol.
     *
     * @return Rol del usuario (Huesped, Gerente, Recepcionista).
     */
    public String getRol() {
        return this.getClass().getSimpleName();
    }

    /**
     * Determina si el usuario es un administrador (gerente).
     *
     * @return {@code true} si es instancia de {@link Gerente}.
     */
    public boolean esAdministrador() {
        return this instanceof Gerente;
    }

    /**
     * Cambia la contraseña actual del usuario.
     *
     * @param nuevaContrasenia Nueva contraseña que se desea establecer.
     */
    public void cambiarContrasenia(String nuevaContrasenia) {
        this.contrasenia = nuevaContrasenia;
    }

    /**
     * Desactiva la cuenta del usuario, impidiendo su uso posterior.
     */
    public void desactivarCuenta() {
        this.activo = false;
    }
}