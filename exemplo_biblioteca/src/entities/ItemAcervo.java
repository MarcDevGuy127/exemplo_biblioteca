package entities;

public class ItemAcervo {
	private String titulo, ano;
	private int codigo;

	private String getTitulo() {
		return titulo;
	}

	private void setTitulo(String titulo) {
		if (titulo != "") {
			this.titulo = titulo;
		}
	}

	private String getAno() {
		return ano;
	}

	private void setAno(String ano) {
		if (ano != "") {
			this.ano = ano;
		}
	}

	private int getCodigo() {
		return codigo;
	}

	private void setCodigo(int codigo) {
		if (codigo > 0) {
			this.codigo = codigo;
		}
	}

}
