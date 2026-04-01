package entities;

public class Usuario {
	private String nome, cidade, bairro, endereco, complemento;
	private int idade;

	private String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		if (nome != "") {
			this.nome = nome;
		}
	}

	private String getCidade() {
		return cidade;
	}

	private void setCidade(String cidade) {
		if (nome != "") {
			this.cidade = cidade;
		}
	}

	private String getBairro() {
		return bairro;
	}

	private void setBairro(String bairro) {
		if (nome != "") {
			this.bairro = bairro;
		}
	}

	private String getEndereco() {
		return endereco;
	}

	private void setEndereco(String endereco) {
		if (endereco != "") {
			this.endereco = endereco;
		}
	}

	private String getComplemento() {
		return complemento;
	}

	private void setComplemento(String complemento) {
		if (complemento != "") {
			this.complemento = complemento;
		}
	}

	private int getIdade() {
		return idade;
	}

	private void setIdade(int idade) {
		if (idade > 0) {
			this.idade = idade;
		}
	}
}
