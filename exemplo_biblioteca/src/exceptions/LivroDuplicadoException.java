package exceptions;

// criando excecao para livro duplicado
public class LivroDuplicadoException extends Exception {

    public LivroDuplicadoException(String mensagem) {
        super(mensagem);
    }
}