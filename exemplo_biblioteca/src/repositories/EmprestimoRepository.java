package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.EmprestimoNaoEncontradoException;
import model.Emprestimo;

public class EmprestimoRepository {

    // consultando histórico completo de empréstimos
    private List<Emprestimo> emprestimos =
            new ArrayList<>();

    // realizando busca por ID
    private Map<Integer, Emprestimo> mapaEmprestimos =
            new HashMap<>();

    // metodo para adicionar emprestimo
    public void adicionarEmprestimo(
            Emprestimo emprestimo) {

        emprestimos.add(emprestimo);

        mapaEmprestimos.put(
                emprestimo.getId(),
                emprestimo);
    }

    // metodo para consultar emprestimo por id
    public Emprestimo buscarPorId(int id)
            throws EmprestimoNaoEncontradoException {

        Emprestimo emprestimo =
                mapaEmprestimos.get(id);

        // condicional para emprestimo nao localizado
        if (emprestimo == null) {

            throw new EmprestimoNaoEncontradoException(
                    "Empréstimo não encontrado.");
        }

        return emprestimo;
    }

    // metodo para atualizar emprestimo
    public void atualizarEmprestimo(
            Emprestimo emprestimo)
            throws EmprestimoNaoEncontradoException {

        if (!mapaEmprestimos.containsKey(
                emprestimo.getId())) {

            throw new EmprestimoNaoEncontradoException(
                    "Empréstimo não encontrado.");
        }

        mapaEmprestimos.put(
                emprestimo.getId(),
                emprestimo);

        for (int i = 0;
             i < emprestimos.size();
             i++) {

            if (emprestimos.get(i).getId()
                    == emprestimo.getId()) {

                emprestimos.set(
                        i,
                        emprestimo);

                return;
            }
        }
    }

    // metodo para listar todos os emprestimos
    public List<Emprestimo> listarTodos() {

        return new ArrayList<>(
                emprestimos);
    }

    // metodo para listar todos os emprestimos ativos
    public List<Emprestimo> listarAtivos() {

        List<Emprestimo> ativos =
                new ArrayList<>();

        for (Emprestimo e : emprestimos) {

            if ("EMPRESTADO".equalsIgnoreCase(
                    e.getStatus())) {

                ativos.add(e);
            }
        }

        return ativos;
    }

    // metodo para listar todos os emprestimos por matricula
    public List<Emprestimo> listarPorMatricula(
            String matricula) {

        List<Emprestimo> resultado =
                new ArrayList<>();

        for (Emprestimo e : emprestimos) {

            if (e.getMatricula()
                    .equalsIgnoreCase(
                            matricula)) {

                resultado.add(e);
            }
        }

        return resultado;
    }

    // metodo para listar todos os emprestimos ativos
    public Emprestimo buscarEmprestimoAtivo(
            String isbn,
            String matricula)
            throws EmprestimoNaoEncontradoException {

        for (Emprestimo e : emprestimos) {

            boolean mesmoLivro =
                    e.getIsbn()
                     .equalsIgnoreCase(isbn);

            boolean mesmoUsuario =
                    e.getMatricula()
                     .equalsIgnoreCase(
                             matricula);

            boolean ativo =
                    "EMPRESTADO"
                    .equalsIgnoreCase(
                            e.getStatus());

            if (mesmoLivro &&
                mesmoUsuario &&
                ativo) {

                return e;
            }
        }

        throw new EmprestimoNaoEncontradoException(
                "Empréstimo ativo não encontrado.");
    }
}