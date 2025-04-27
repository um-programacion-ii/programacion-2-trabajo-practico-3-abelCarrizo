package app.biblioteca.modelos;

import java.time.LocalDate;

/**
 * Representa un préstamo de un libro en la biblioteca.
 */
public class Prestamos {
    private final LocalDate fechaPrestamo;
    private final Libro libro;

    /**
     * Crea un nuevo préstamo para el libro dado.
     * Cambia el estado del libro a PRESTADO.
     *
     * @param libro el libro que se presta
     * @throws IllegalArgumentException si el libro es null o ya está prestado
     */
    public Prestamos(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null");
        }
        if (libro.getEstado() == Estado.PRESTADO) {
            throw new IllegalArgumentException("El libro ya está prestado");
        }
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.libro.setEstado(Estado.PRESTADO);
    }

    /**
     * Obtiene la fecha en la que se realizó el préstamo.
     *
     * @return la fecha del préstamo
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Obtiene el libro asociado a este préstamo.
     *
     * @return el libro prestado
     */
    public Libro getLibro() {
        return libro;
    }
}
