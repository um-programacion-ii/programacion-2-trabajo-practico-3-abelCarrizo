package app.biblioteca.recursos;

import app.biblioteca.utils.Estado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    @Test
    void testCrearLibroValido() {
        // Crear un libro valido
        Libro libro = new Libro ("978-3-16-148410-0", "Clean Code", "Robert C. Martin");

        //Verificar que los valores se asignen correctamente
        assertEquals("978-3-16-148410-0", libro.getIsbn());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }
}
