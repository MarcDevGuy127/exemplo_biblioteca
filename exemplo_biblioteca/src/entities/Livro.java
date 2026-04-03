package entities;

public class Livro extends ItemAcervo {
	private String titulo, autor, editora;
	private int numPaginas;
	
	// construtor com parametros
	public Livro(String titulo, String autor, String editora, int numPaginas) {
		super(titulo, editora, numPaginas); // chamando superclasse
		this.autor = autor;
		this.numPaginas = numPaginas;
	}
	
	// sobrescrita
	@Override
	public String exibirResumo() {
		// TODO Auto-generated method stub
		return super.exibirResumo();
	}
	
	// getters e setters
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
}
