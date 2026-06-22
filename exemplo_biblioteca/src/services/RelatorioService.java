package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.List;

import exceptions.LivroNaoEncontradoException;
import exceptions.UsuarioNaoEncontradoException;
import factory.MultaStrategyFactory;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import repositories.LivroRepository;
import repositories.UsuarioRepository;
import strategies.CalculadoraMultaStrategy;

public class RelatorioService {

    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;

    public RelatorioService(
            LivroRepository livroRepository,
            UsuarioRepository usuarioRepository) {

        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void gerarRelatorio(
            List<Emprestimo> emprestimos,
            String caminho) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {

            bw.write("isbn;titulo;matricula;nomeUsuario;status;diasAtraso;valorMulta");
            bw.newLine();

            for (Emprestimo emprestimo : emprestimos) {

                // processando usuarios de forma segura
                Usuario usuario;

                try {
                    usuario = usuarioRepository.buscarPorMatricula(
                            emprestimo.getMatricula()
                    );
                } catch (UsuarioNaoEncontradoException e) {
                    usuario = null;
                }

                // processando livros de forma segura
                Livro livro;

                try {
                    livro = livroRepository.buscarPorIsbn(
                            emprestimo.getIsbn()
                    );
                } catch (LivroNaoEncontradoException e) {
                    livro = null;
                }

                // processando dados de forma segura
                String nomeUsuario =
                        (usuario != null) ? usuario.getNome() : "DESCONHECIDO";

                String tituloLivro =
                        (livro != null) ? livro.getTitulo() : "DESCONHECIDO";

                // processando calculo de dias de atraso
                long diasAtraso = 0;

                if (emprestimo.getDataDevolucao() != null &&
                    emprestimo.getDataPrevistaDevolucao() != null) {

                    diasAtraso = ChronoUnit.DAYS.between(
                            emprestimo.getDataPrevistaDevolucao(),
                            emprestimo.getDataDevolucao()
                    );
                }

                if (diasAtraso < 0) {
                    diasAtraso = 0;
                }

                // utilizando metodo do strategy
                CalculadoraMultaStrategy strategy =
                        MultaStrategyFactory.criar(
                                usuario != null ? usuario : new model.Aluno(
                                        emprestimo.getMatricula(),
                                        nomeUsuario
                                )
                        );

                double multa = strategy.calcularMulta(emprestimo);

                // metodo para registrar relatorio
                bw.write(
                        emprestimo.getIsbn() + ";" +
                        tituloLivro + ";" +
                        emprestimo.getMatricula() + ";" +
                        nomeUsuario + ";" +
                        emprestimo.getStatus() + ";" +
                        diasAtraso + ";" +
                        multa
                );

                bw.newLine();
            }
        }
    }
}