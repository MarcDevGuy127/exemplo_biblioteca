package exceptions;

// criando excecao para usuario nao encontrado
public class UsuarioNaoEncontradoException extends Exception {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
