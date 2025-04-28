package app.biblioteca.servicios;


import app.biblioteca.modelos.Libro;
import app.biblioteca.modelos.Prestamo;
import app.biblioteca.modelos.Usuario;
import app.biblioteca.modelos.Estado;

/**
 * Servicio para gestionar usuarios y sus préstamos.
 */
public class GestionUsuarios {
    private final Catalogo catalogo;
    private final SistemaPrestamos sistemaPrestamos;

    /**
     * Constructor del servicio de gestión de usuarios.
     *
     * @param catalogo         el catálogo de libros
     * @param sistemaPrestamos el servicio de préstamos
     */
    public GestionUsuarios(Catalogo catalogo, SistemaPrestamos sistemaPrestamos) {
        this.catalogo = catalogo;
        this.sistemaPrestamos = sistemaPrestamos;
    }

    /**
     * Registra un préstamo para un usuario dado.
     * Busca el libro por ISBN, crea el préstamo y lo añade al historial del usuario.
     *
     * @param usuario el usuario que solicita el préstamo
     * @param isbn    el ISBN del libro a prestar
     * @throws IllegalArgumentException si el usuario es null
     */
    public void registrarPrestamo(Usuario usuario, String isbn) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }

        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro == null || libro.getEstado() != Estado.DISPONIBLE) {
            throw new IllegalArgumentException("El libro con ISBN " + isbn + " no está disponible.");
        }

        Prestamo prestamo = sistemaPrestamos.prestarLibro(isbn);
        usuario.agregarPrestamo(prestamo);
    }
}
