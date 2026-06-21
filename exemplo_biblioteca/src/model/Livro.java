package model;

public class Livro {

    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private int ano;
    private boolean disponibilidade;

    // definindo cada objeto livro
    public Livro(String isbn, String titulo, String autor, String categoria, int ano, boolean disponibilidade) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ano = ano;
        this.disponibilidade = disponibilidade;
    }

    // isbn pode ser consultado e alterado
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // titulo pode ser consultado e alterado
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // autor pode ser consultado e alterado
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    // categoria pode ser consultada e alterada
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // ano pode ser consultado e alterado
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    // disponibilidade pode ser consultada e alterada
    public boolean isDisponivel() {
        return disponibilidade;
    }

    public void setDisponivel(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}