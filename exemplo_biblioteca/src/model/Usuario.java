package model;

public abstract class Usuario {

    private String matricula;
    private String nome;
    //private String curso;

    // definindo cada objeto usuario
    public Usuario(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        //this.curso = curso;
    }

    // aqui matricula eh imutavel e preservada, apenas consultada.
    public String getMatricula() {
        return matricula;
    }
    
    // nome do usuario pode ser consultado e alterado
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // o curso pode ser consultado, porem nesse caso ele sera imultavel e preservado.
    /* public String getCurso() {
		return curso;
	}*/

    // consultando tipo de usuario
	public abstract String getTipoUsuario();

	// convertendo metodos para String
    @Override
    public String toString() {
        return getMatricula()
                + " - "
                + getNome()
                + " - "
                // + getCurso()
                // + " - "
                + getTipoUsuario();
    }
}