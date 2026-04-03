package entities;

public class ItemAcervo {
	private String titulo, ano;
	private int codigo;

	// construtor com parametros
	public ItemAcervo(String titulo, String ano, int codigo) {
		this.titulo = titulo;
		this.ano = ano;
		this.codigo = codigo;
	}

	public String exibirResumo() {
		return "Titulo:" + getTitulo() + "Ano:" + getAno() + "Codigo:" + getCodigo();
	}
	
	// getters e setters
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
