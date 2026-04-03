package entities;

public class Usuario {
	private String nome, cidade, bairro, endereco, complemento;
	private int idade;

	// construtor com parametros
	public Usuario(String nome,
			String cidade,
			String bairro,
			String endereco,
			String complemento,
			int idade) {
			this.nome = nome;
			this.cidade = cidade;
			this.bairro = bairro;
			this.endereco = endereco;
			this.complemento = complemento;
			this.idade = idade;
	}

	// getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
