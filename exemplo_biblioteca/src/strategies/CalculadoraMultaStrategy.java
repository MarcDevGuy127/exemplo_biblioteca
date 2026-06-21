package strategies;

import model.Emprestimo;

// definindo contrato para calculo das multas
public interface CalculadoraMultaStrategy {

	// definindo metodo de calculo para multa
    double calcularMulta(Emprestimo emprestimo);

}