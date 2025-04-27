package app.biblioteca.servicios;

import app.biblioteca.modelos.Estado;
import app.biblioteca.modelos.Libro;
import app.biblioteca.modelos.Prestamo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SistemaPrestamosTest {

    private AutoCloseable mocks;

    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void testPrestarLibro_Exitoso() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");

        assertNotNull(prestamo, "El préstamo no debería ser null");
        assertEquals(libro, prestamo.getLibro(), "El libro del préstamo debe ser el mismo");
        assertEquals(Estado.PRESTADO, libro.getEstado(), "El libro debe quedar en estado PRESTADO");
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
    }

    @Test
    void testPrestarLibro_LibroNoEncontrado() {
        when(catalogo.buscarPorIsbn("000-0-00-000000-0")).thenReturn(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> sistemaPrestamos.prestarLibro("000-0-00-000000-0")
        );
        assertTrue(ex.getMessage().contains("No se encontró un libro"));
        verify(catalogo).buscarPorIsbn("000-0-00-000000-0");
    }

    @Test
    void testPrestarLibro_LibroYaPrestado() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro.setEstado(Estado.PRESTADO);
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> sistemaPrestamos.prestarLibro("978-3-16-148410-0")
        );
        assertTrue(ex.getMessage().contains("ya está prestado"));
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
    }

    @Test
    void testDevolverLibro_Exitoso() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro.setEstado(Estado.PRESTADO);
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        sistemaPrestamos.devolverLibro("978-3-16-148410-0");

        assertEquals(Estado.DISPONIBLE, libro.getEstado(), "El libro debe quedar en estado DISPONIBLE");
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
    }

    @Test
    void testDevolverLibro_LibroNoEncontrado() {
        when(catalogo.buscarPorIsbn("000-0-00-000000-0")).thenReturn(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> sistemaPrestamos.devolverLibro("000-0-00-000000-0")
        );
        assertTrue(ex.getMessage().contains("No se encontró un libro"));
        verify(catalogo).buscarPorIsbn("000-0-00-000000-0");
    }

    @Test
    void testDevolverLibro_NoPrestado() {
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> sistemaPrestamos.devolverLibro("978-3-16-148410-0")
        );
        assertTrue(ex.getMessage().contains("no está prestado"));
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
    }
}
