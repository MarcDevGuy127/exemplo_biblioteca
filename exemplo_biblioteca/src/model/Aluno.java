package model;

public class Aluno extends Usuario {

	// definindo usuarios de aluno como tipo aluno
    public static final String TIPO = "ALUNO";

    // inserindo dados do construtor Aluno nas propriedades de Usuario
    public Aluno(String matricula, String nome, String curso) {
        super(matricula, nome, curso);
    }

    // consultando tipo do usuario
    @Override
    public String getTipoUsuario() {
        return TIPO;
    }

	// convertendo metodos para String
    @Override
    public String toString() {
        return getMatricula()
                + " - "
                + getNome()
                + " - "
                + getCurso()
                + " - "
                + getTipoUsuario();
    }
}