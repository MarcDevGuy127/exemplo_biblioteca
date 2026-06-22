package exceptions;

// criando excecao para livro nao encontrado
public class LivroNaoEncontradoException extends Exception {

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}