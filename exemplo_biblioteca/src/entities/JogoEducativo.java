package entities;

import entities.ItemAcervo;

public class JogoEducativo extends ItemAcervo {
	private String nome, descricao;
	private int pecas;

	public JogoEducativo(String nome, String descricao, int pecas) {
		this.nome = nome;
		this.descricao = descricao;
		this.pecas = pecas;
	}
	/*
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != "") {
			this.nome = nome;
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao != "") {
			this.descricao = descricao;
		}
	}

	public int getPecas() {
		return pecas;
	}

	public void setPecas(int pecas) {
		if (pecas != 0) {
			this.pecas = pecas;
		}
	}*/

}
