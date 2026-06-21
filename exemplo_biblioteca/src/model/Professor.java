package model;

public class Professor extends Usuario {

	// definindo usuarios de professor como tipo professor
    public static final String TIPO = "PROFESSOR";

    // inserindo dados do construtor Professor nas propriedades de Usuario
    public Professor(String matricula, String nome, String curso) {
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