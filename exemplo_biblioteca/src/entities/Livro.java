package entities;

public class Livro {
	private String titulo, autor, editora;
	private int numPaginas;
	private String getTitulo() {
		return titulo;
	}
	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	private String getAutor() {
		return autor;
	}
	private void setAutor(String autor) {
		this.autor = autor;
	}
	private String getEditora() {
		return editora;
	}
	private void setEditora(String editora) {
		this.editora = editora;
	}
	private int getNumPaginas() {
		return numPaginas;
	}
	private void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
}
