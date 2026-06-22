package exceptions;

// criando excecao para formato invalido
public class CsvFormatoInvalidoException extends Exception {

    public CsvFormatoInvalidoException(String mensagem) {
        super(mensagem);
    }
}