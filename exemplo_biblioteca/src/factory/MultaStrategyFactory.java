package factory;

import model.Aluno;
import model.Usuario;
import strategies.CalculadoraMultaStrategy;
import strategies.MultaAlunoStrategy;
import strategies.MultaProfessorStrategy;

public class MultaStrategyFactory {
	
	// garantindo que cada usuario cadastrado tenha uma calculadora de multa
    public static CalculadoraMultaStrategy criar(Usuario usuario) {

    	// se usuario for tipo aluno, crie uma calculadora de multa para Alunos
        if (usuario instanceof Aluno) {
            return new MultaAlunoStrategy();
        }

    	// senao for aluno o usuario eh tipo professor
        // entao retorne a criacao de uma calculadora de multa para Professores
        return new MultaProfessorStrategy();
    }
}