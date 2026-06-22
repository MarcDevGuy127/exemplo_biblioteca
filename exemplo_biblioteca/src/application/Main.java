package application;
import java.util.Scanner;

import exceptions.LivroDuplicadoException;
import exceptions.LivroIndisponivelException;
import exceptions.LivroNaoEncontradoException;
import exceptions.UsuarioDuplicadoException;
import exceptions.UsuarioNaoEncontradoException;
import model.Livro;
import model.Usuario;
import repositories.EmprestimoRepository;
import repositories.LivroRepository;
import repositories.UsuarioRepository;
import services.ArquivoCsvService;
import services.BibliotecaService;
import services.RelatorioService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LivroRepository livroRepository = new LivroRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

        BibliotecaService bibliotecaService =
                new BibliotecaService(
                        livroRepository,
                        usuarioRepository,
                        emprestimoRepository);

        ArquivoCsvService csvService = new ArquivoCsvService();

        RelatorioService relatorioService =
                new RelatorioService(livroRepository, usuarioRepository);

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Carregar dados dos CSV");
            System.out.println("2 - Cadastrar livro");
            System.out.println("3 - Cadastrar usuario");
            System.out.println("4 - Listar livros");
            System.out.println("5 - Buscar livro por ISBN");
            System.out.println("6 - Emprestar livro");
            System.out.println("7 - Devolver livro");
            System.out.println("8 - Remover livro");
            System.out.println("9 - Salvar dados nos CSV");
            System.out.println("10 - Gerar relatório");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");

            try {

                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {

                    case 1:
                        System.out.println("Carregando CSV...");
                        break;

                    case 2:
                        cadastrarLivro(sc, bibliotecaService);
                        break;

                    case 3:
                        cadastrarUsuario(sc, bibliotecaService);
                        break;

                    case 4:
                        listarLivros(livroRepository);
                        break;

                    case 5:
                        buscarLivro(sc, livroRepository);
                        break;

                    case 6:
                        emprestarLivro(sc, bibliotecaService);
                        break;

                    case 7:
                        devolverLivro(sc, bibliotecaService);
                        break;

                    case 8:
                        removerLivro(sc, livroRepository);
                        break;

                    case 9:
                        System.out.println("Salvando CSV...");
                        break;

                    case 10:
                        try {

                            relatorioService.gerarRelatorio(
                                    emprestimoRepository.listarTodos(),
                                    "relatorio.csv"
                            );

                            System.out.println("Relatório gerado com sucesso!");

                        } catch (Exception e) {
                            System.out.println("Erro ao gerar relatório: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Sistema encerrado.");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        sc.close();
    }

    // metodo para cadastrar livro
    private static void cadastrarLivro(
            Scanner sc,
            BibliotecaService service) {

        try {

            System.out.print("ISBN: ");
            String isbn = sc.nextLine();

            System.out.print("Título: ");
            String titulo = sc.nextLine();

            System.out.print("Autor: ");
            String autor = sc.nextLine();

            System.out.print("Categoria: ");
            String categoria = sc.nextLine();

            System.out.print("Ano: ");
            int ano = Integer.parseInt(sc.nextLine());

            service.cadastrarLivro(isbn, titulo, autor, categoria, ano);

            System.out.println("Livro cadastrado com sucesso!");

        } catch (LivroDuplicadoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar livro.");
        }
    }

    // metodo para cadastrar usuario
    private static void cadastrarUsuario(
            Scanner sc,
            BibliotecaService service) {

        try {

            System.out.print("Matrícula: ");
            String matricula = sc.nextLine();

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Tipo (ALUNO/PROFESSOR): ");
            String tipo = sc.nextLine();

            Usuario usuario;

            if (tipo.equalsIgnoreCase("ALUNO")) {
                usuario = new model.Aluno(matricula, nome);
            } else {
                usuario = new model.Professor(matricula, nome);
            }

            service.cadastrarUsuario(usuario);

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (UsuarioDuplicadoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário.");
        }
    }

    // metodo para emprestimo de livro
    private static void emprestarLivro(
            Scanner sc,
            BibliotecaService service) {

        try {

            System.out.print("ISBN: ");
            String isbn = sc.nextLine();

            System.out.print("Matrícula: ");
            String matricula = sc.nextLine();

            service.emprestarLivro(isbn, matricula);

            System.out.println("Empréstimo realizado!");

        } catch (LivroNaoEncontradoException |
                 UsuarioNaoEncontradoException |
                 LivroIndisponivelException e) {

            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("Erro ao emprestar livro.");
        }
    }

    // metodo para devolucao de livros
    private static void devolverLivro(
            Scanner sc,
            BibliotecaService service) {

        try {

            System.out.print("ISBN: ");
            String isbn = sc.nextLine();

            System.out.print("Matrícula: ");
            String matricula = sc.nextLine();

            double multa = service.devolverLivro(isbn, matricula);

            System.out.println("Livro devolvido com sucesso!");
            System.out.println("Multa aplicada: R$ " + multa);

        } catch (Exception e) {
            System.out.println("Erro na devolução: " + e.getMessage());
        }
    }

    // metodo para listar livros
    private static void listarLivros(LivroRepository repo) {

        if (repo.listarTodos().isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro l : repo.listarTodos()) {
            System.out.println(
                    l.getIsbn() + " - " +
                    l.getTitulo() + " - " +
                    (l.isDisponivel() ? "Disponível" : "Emprestado"));
        }
    }

    // metodo para consultar livros 
    private static void buscarLivro(
            Scanner sc,
            LivroRepository repo) {

        try {

            System.out.print("ISBN: ");
            String isbn = sc.nextLine();

            Livro livro = repo.buscarPorIsbn(isbn);

            System.out.println(livro.getTitulo());

        } catch (LivroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // metodo para remover livros
    private static void removerLivro(
            Scanner sc,
            LivroRepository repo) {

        try {

            System.out.print("ISBN: ");
            String isbn = sc.nextLine();

            repo.removerLivro(isbn);

            System.out.println("Livro removido.");

        } catch (LivroNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}