package services;

import java.time.LocalDate;

import exceptions.LivroDuplicadoException;
import exceptions.LivroIndisponivelException;
import exceptions.LivroNaoEncontradoException;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNaoEncontradoException;
import factory.MultaStrategyFactory;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import repositories.EmprestimoRepository;
import repositories.LivroRepository;
import repositories.UsuarioRepository;
import strategies.CalculadoraMultaStrategy;

public class BibliotecaService {

    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;
    private EmprestimoRepository emprestimoRepository;

    private int contadorId = 1;

    public BibliotecaService(
            LivroRepository livroRepository,
            UsuarioRepository usuarioRepository,
            EmprestimoRepository emprestimoRepository) {

        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    // metodo para cadastrar livros
    public void cadastrarLivro(
            String isbn,
            String titulo,
            String autor,
            String categoria,
            int ano)
            throws LivroDuplicadoException {

        Livro livro = new Livro(
                isbn, titulo, autor, categoria, ano, true);

        livroRepository.adicionarLivro(livro);
    }

    // metodo para cadastrar usuarios
    public void cadastrarUsuario(Usuario usuario)
            throws UsuarioDuplicadoException {

        usuarioRepository.adicionarUsuario(usuario);
    }

    // metodo para emprestar livros
    public void emprestarLivro(
            String isbn,
            String matricula)
            throws LivroNaoEncontradoException,
                   UsuarioNaoEncontradoException,
                   LivroIndisponivelException {

        Livro livro = livroRepository.buscarPorIsbn(isbn);
        Usuario usuario = usuarioRepository.buscarPorMatricula(matricula);

        if (!livro.isDisponivel()) {
            throw new LivroIndisponivelException("Livro indisponível.");
        }

        int prazoDias =
                (usuario instanceof model.Aluno) ? 7 : 14;

        livro.setDisponivel(false);

        Emprestimo emprestimo = new Emprestimo(
                contadorId++,
                isbn,
                matricula,
                LocalDate.now(),
                LocalDate.now().plusDays(prazoDias),
                null,
                "EMPRESTADO"
        );

        emprestimoRepository.adicionarEmprestimo(emprestimo);
    }

    // metodo para calcular multa toda vez que um livro for devolvido
    public double devolverLivro(
            String isbn,
            String matricula)
            throws Exception {

        Emprestimo emprestimo =
                emprestimoRepository.buscarEmprestimoAtivo(
                        isbn, matricula);

        Livro livro =
                livroRepository.buscarPorIsbn(isbn);

        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo.setStatus("DEVOLVIDO");

        livro.setDisponivel(true);

        emprestimoRepository.atualizarEmprestimo(emprestimo);

        // consultando usuarios cadastrados
        Usuario usuario =
                usuarioRepository.buscarPorMatricula(matricula);

        // metodo para calcular multa para tipo usuario usando strategies
        CalculadoraMultaStrategy strategy =
                MultaStrategyFactory.criar(usuario);

        // retornando o calculo para cada emprestimo
        return strategy.calcularMulta(emprestimo);
    }
}