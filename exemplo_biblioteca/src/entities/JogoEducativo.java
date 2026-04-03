package entities;

public class JogoEducativo extends ItemAcervo {
	private String nome, descricao;
	private int pecas;
	
	// construtor com parametros
	public JogoEducativo(String nome, String descricao, int pecas) {
		super(nome, descricao, pecas);
		this.nome = nome;
		this.descricao = descricao;
		this.pecas = pecas;
	}
	
	// getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPecas() {
		return pecas;
	}
	public void setPecas(int pecas) {
		this.pecas = pecas;
	}

}
