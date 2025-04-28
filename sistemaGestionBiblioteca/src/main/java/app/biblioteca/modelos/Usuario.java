package app.biblioteca.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa un usuario de la biblioteca.
 */
public class Usuario {
    private String nombre;
    private final List<Prestamo> historialPrestamos;

    /**
     * Crea un nuevo usuario con el nombre dado.
     *
     * @param nombre el nombre del usuario
     * @throws IllegalArgumentException si el nombre es null o vacío
     */
    public Usuario(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser vacío");
        }
        this.nombre = nombre;
        this.historialPrestamos = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre Nuevo nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Registra un nuevo préstamo en el historial del usuario.
     *
     * @param prestamo el objeto Prestamo a registrar
     * @throws IllegalArgumentException si prestamo es null
     */
    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null");
        }
        this.historialPrestamos.add(prestamo);
    }

    /**
     * Obtiene una lista inmodificable con el historial de préstamos.
     *
     * @return lista de préstamos realizados por el usuario
     */
    public List<Prestamo> getHistorialPrestamos() {
        return Collections.unmodifiableList(historialPrestamos);
    }
}
