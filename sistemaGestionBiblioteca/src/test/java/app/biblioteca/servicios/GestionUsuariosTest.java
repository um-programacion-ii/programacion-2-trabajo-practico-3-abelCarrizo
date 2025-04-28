package app.biblioteca.servicios;

import app.biblioteca.modelos.Estado;
import app.biblioteca.modelos.Libro;
import app.biblioteca.modelos.Prestamo;
import app.biblioteca.modelos.Usuario;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestionUsuariosTest {
    private AutoCloseable mocks;

    @Mock
    private Catalogo catalogo;

    @Mock
    private SistemaPrestamos sistemaPrestamos;

    @InjectMocks
    private GestionUsuarios gestionUsuarios;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void testRegistrarPrestamo_Exitoso() {
        Usuario usuario = new Usuario("usuario1");
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");

        Prestamo prestamoMock = mock(Prestamo.class);

        when(catalogo.buscarPorIsbn("978-3-16-148410-0"))
                .thenReturn(libro);
        when(sistemaPrestamos.prestarLibro("978-3-16-148410-0"))
                .thenReturn(prestamoMock);

        gestionUsuarios.registrarPrestamo(usuario, "978-3-16-148410-0");

        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
        verify(sistemaPrestamos).prestarLibro("978-3-16-148410-0");
        assertEquals(1, usuario.getHistorialPrestamos().size());
        assertSame(prestamoMock, usuario.getHistorialPrestamos().get(0));
    }


    @Test
    void testRegistrarPrestamo_UsuarioNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarPrestamo(null, "978-3-16-148410-0");
        });
        assertEquals("El usuario no puede ser null", exception.getMessage());
    }

    @Test
    void testRegistrarPrestamo_LibroNoDisponible() {
        Usuario usuario = new Usuario("usuario1");
        Libro libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro.setEstado(Estado.PRESTADO);

        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarPrestamo(usuario, "978-3-16-148410-0");
        });
        assertEquals("El libro con ISBN 978-3-16-148410-0 no está disponible.", exception.getMessage());
    }

    @Test
    void testRegistrarPrestamo_LibroNoExiste() {

        Usuario usuario = new Usuario("usuario1");

        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gestionUsuarios.registrarPrestamo(usuario, "978-3-16-148410-0");
        });
        assertEquals("El libro con ISBN 978-3-16-148410-0 no está disponible.", exception.getMessage());
    }
}