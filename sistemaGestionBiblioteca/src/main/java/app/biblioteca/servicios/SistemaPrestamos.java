package app.biblioteca.servicios;

import app.biblioteca.modelos.Estado;
import app.biblioteca.modelos.Libro;
import app.biblioteca.modelos.Prestamo;

/**
 * Servicio para gestionar préstamos de libros.
 */
public class SistemaPrestamos {
    private final Catalogo catalogo;

    /**
     * Crea el Sistema de Prestamos.
     *
     * @param catalogo el catálogo de libros a usar
     */
    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    /**
     * Realiza el préstamo de un libro dado su ISBN.
     *
     * @param isbn el ISBN del libro a prestar
     * @return el objeto Prestamo que registra la operación
     * @throws IllegalArgumentException si el ISBN no existe o el libro ya está prestado
     */
    public Prestamo prestarLibro(String isbn) {
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("No se encontró un libro con ISBN: " + isbn);
        }
        return new Prestamo(libro);
    }

    /**
     * Realiza la devolución de un libro dado su ISBN.
     * Cambia el estado del libro a DISPONIBLE.
     *
     * @param isbn el ISBN del libro a devolver
     * @throws IllegalArgumentException si el ISBN no existe o el libro no está prestado
     */
    public void devolverLibro(String isbn) {
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("No se encontró un libro con ISBN: " + isbn);
        }
        if (libro.getEstado() != Estado.PRESTADO) {
            throw new IllegalArgumentException("El libro con ISBN " + isbn + " no está prestado");
        }
        libro.setEstado(Estado.DISPONIBLE);
    }
}
