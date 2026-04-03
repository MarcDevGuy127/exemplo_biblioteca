package entities;

import entities.ItemAcervo;

public class JogoEducativo extends ItemAcervo {
	private String nome, descricao;
	private int pecas;

	private String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		if (nome != "") {
			this.nome = nome;
		}
	}

	private String getDescricao() {
		return descricao;
	}

	private void setDescricao(String descricao) {
		if (descricao != "") {
			this.descricao = descricao;
		}
	}

	private int getPecas() {
		return pecas;
	}

	private void setPecas(int pecas) {
		if (pecas != 0) {
			this.pecas = pecas;
		}
	}

}
