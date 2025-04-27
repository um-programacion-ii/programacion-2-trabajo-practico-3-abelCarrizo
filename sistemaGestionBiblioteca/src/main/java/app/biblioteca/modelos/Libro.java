package app.biblioteca.modelos;

/**
 * Representa un libro dentro de la biblioteca, con información básica
 * como ISBN, título, autor y su estado actual (por ejemplo, disponible o prestado).
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private Estado estado;

    /**
     * Crea un nuevo libro con el estado inicial DISPONIBLE.
     *
     * @param isbn   Código ISBN único del libro
     * @param titulo Título del libro
     * @param autor  Autor del libro
     */
    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = Estado.DISPONIBLE;
    }

    /**
     * Obtiene el ISBN del libro.
     *
     * @return ISBN del libro
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del libro.
     *
     * @param isbn Nuevo ISBN a asignar
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return título del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo Nuevo título a asignar
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return autor del libro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor Nuevo autor a asignar
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el estado actual del libro.
     *
     * @return estado del libro
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del libro.
     *
     * @param estado Nuevo estado a asignar
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
