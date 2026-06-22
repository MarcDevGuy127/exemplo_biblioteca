package exceptions;

// criando excecao para usuarios duplicados
public class UsuarioDuplicadoException extends Exception {

    public UsuarioDuplicadoException(String mensagem) {
        super(mensagem);
    }
}