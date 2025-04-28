package app.biblioteca.servicios;

import app.biblioteca.modelos.Estado;
import app.biblioteca.modelos.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa un catálogo de libros disponible en la biblioteca.
 * Permite buscar libros por su ISBN y obtener libros que estén disponibles para préstamo.
 */
public class Catalogo {
    private final List<Libro> libros;

    public Catalogo() {
        this.libros = new ArrayList<Libro>();
    }

    /**
     * Agrega un libro al catálogo.
     * @param libro el libro a agregar
     */
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    /**
     * Busca un libro por su ISBN.
     * @param isbn ISBN a buscar
     * @return el libro encontrado, o null si no existe
     */
    public Libro buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(libro -> libro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    /**
     * Devuelve una lista con todos los libros cuyo estado sea DISPONIBLE.
     * @return lista de libros disponibles
     */
    public List<Libro> obtenerLibrosDisponibles() {
        return libros.stream()
                .filter(libro -> libro.getEstado() == Estado.DISPONIBLE)
                .collect(Collectors.toList());
    }
}
