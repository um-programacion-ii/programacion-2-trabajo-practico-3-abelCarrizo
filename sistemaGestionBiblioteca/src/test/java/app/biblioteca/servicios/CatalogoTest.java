package app.biblioteca.servicios;

import app.biblioteca.modelos.Estado;
import app.biblioteca.modelos.Libro;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CatalogoTest {
    private Catalogo catalogo;
    private Libro libro1;
    private Libro libro2;
    private Libro libro3;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro2 = new Libro("978-0-13-235088-4", "Clean Architecture", "Robert C. Martin");
        libro3 = new Libro("978-0-13-600663-3", "Sistemas Operativos", "William Stallings");

        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
        catalogo.agregarLibro(libro3);
    }

    @Test
    void testBuscarPorISBN_Exitoso() {
        Libro encontrado = catalogo.buscarPorIsbn("978-3-16-148410-0");
        assertNotNull(encontrado, "El libro debería encontrarse en el catálogo");
        assertEquals("Clean Code", encontrado.getTitulo());
    }

    @Test
    void testBuscarPorISBN_Fallido() {
        Libro encontrado = catalogo.buscarPorIsbn("000-0-00-000000-0");
        assertNull(encontrado, "No debería encontrar el libro");
    }

    @Test
    void testObtenerLibrosDisponibles_TodosDisponibles() {
        List<Libro> disponible = catalogo.obtenerLibrosDisponibles();
        assertEquals(3, disponible.size());
        assertTrue(disponible.contains(libro1));
        assertTrue(disponible.contains(libro2));
        assertTrue(disponible.contains(libro3));
    }

    @Test
    void testObtenerLibrosDisponibles_AlgunosPrestados() {
        libro2.setEstado(Estado.PRESTADO);

        List<Libro> disponibles = catalogo.obtenerLibrosDisponibles();
        assertEquals(2, disponibles.size(), "Solo deben quedar dos libros disponibles");
        assertEquals(libro1, disponibles.get(0));
        assertEquals(libro3, disponibles.get(1));
    }
}
