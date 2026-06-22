package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.LivroDuplicadoException;
import exceptions.LivroNaoEncontradoException;
import model.Livro;

public class LivroRepository {

    // armazenando os livros na ordem de cadastro
    private List<Livro> livros = new ArrayList<>();

    // impedindo ISBN duplicado
    private Set<String> isbns = new HashSet<>();

    // busca rápida por ISBN
    private Map<String, Livro> mapaLivros = new HashMap<>();

    // metodo para adicionar livro
    public void adicionarLivro(Livro livro)
            throws LivroDuplicadoException {

        if (isbns.contains(livro.getIsbn())) {

            throw new LivroDuplicadoException(
                    "ISBN já cadastrado.");
        }

        livros.add(livro);

        isbns.add(livro.getIsbn());

        mapaLivros.put(
                livro.getIsbn(),
                livro);
    }

    // metodo para consultar livros por isbn
    public Livro buscarPorIsbn(String isbn)
            throws LivroNaoEncontradoException {

        Livro livro =
                mapaLivros.get(isbn);

        if (livro == null) {

            throw new LivroNaoEncontradoException(
                    "Livro não encontrado.");
        }

        return livro;
    }

    // metodo para verificar se existe livro no acervo
    public boolean existeIsbn(String isbn) {

        return isbns.contains(isbn);
    }

    // metodo para remover livro do acervo
    public void removerLivro(String isbn)
            throws LivroNaoEncontradoException {

        Iterator<Livro> iterator =
                livros.iterator();

        while (iterator.hasNext()) {

            Livro livro =
                    iterator.next();

            if (livro.getIsbn()
                    .equalsIgnoreCase(isbn)) {

                iterator.remove();

                isbns.remove(isbn);

                mapaLivros.remove(isbn);

                return;
            }
        }

        throw new LivroNaoEncontradoException(
                "Livro não encontrado.");
    }

	// metodo para listar todos os livros
    public List<Livro> listarTodos() {

        return new ArrayList<>(livros);
    }

    // metodo para listar apenas livros disponiveis
    public List<Livro> listarDisponiveis() {

        List<Livro> disponiveis =
                new ArrayList<>();

        for (Livro livro : livros) {

            if (livro.isDisponivel()) {

                disponiveis.add(livro);
            }
        }

        return disponiveis;
    }

    // metodo para listar apendas livros emprestados
    public List<Livro> listarEmprestados() {

        List<Livro> emprestados =
                new ArrayList<>();

        for (Livro livro : livros) {

            if (!livro.isDisponivel()) {

                emprestados.add(livro);
            }
        }

        return emprestados;
    }

    // metodo para consultar o total de livros no acervo
    public int quantidadeLivros() {

        return livros.size();
    }
}