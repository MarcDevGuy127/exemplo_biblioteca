package strategies;

import model.Emprestimo;
import java.time.temporal.ChronoUnit;

//definindo contrato para calculo das multas para Professores
public class MultaProfessorStrategy implements CalculadoraMultaStrategy {

    private static final double VALOR_DIARIO = 0.50;

    // sobrescrevendo metodo para calcular multa
    @Override
    public double calcularMulta(Emprestimo emprestimo) {

    	// verificando intervalo entre os dias de devolucao e previsao de devolucao
        long diasAtraso = ChronoUnit.DAYS.between(
                emprestimo.getDataPrevistaDevolucao(),
                emprestimo.getDataDevolucao()
        );

        if (diasAtraso <= 0) {
            return 0.0;
        }

        return diasAtraso * VALOR_DIARIO;
    }
}