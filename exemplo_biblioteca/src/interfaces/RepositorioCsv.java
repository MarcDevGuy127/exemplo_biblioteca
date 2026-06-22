package interfaces;

import java.util.List;

// definindo contrato para o csv
public interface RepositorioCsv<T> {

	// metodo para salvar dados
    void salvar(String caminho);

    // metodo para carregar csv
    List<T> carregar(String caminho);
}