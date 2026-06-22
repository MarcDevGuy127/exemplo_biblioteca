package exceptions;

// criando excecao para livro nao encontrado
public class LivroIndisponivelException extends Exception {

    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
