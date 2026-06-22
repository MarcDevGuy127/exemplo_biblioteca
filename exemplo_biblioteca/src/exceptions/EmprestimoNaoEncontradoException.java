package exceptions;

// criando excecao para emprestimo nao encontrado
public class EmprestimoNaoEncontradoException extends Exception {

    public EmprestimoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}