package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import exceptions.CsvFormatoInvalidoException;
import model.Livro;

public class ArquivoCsvService {

    //  processando livros no arquivo csv
    public List<Livro> carregarLivros(String caminho)
            throws Exception {

        List<Livro> livros = new ArrayList<>();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(caminho))) {

            String linha;

            // pulando linha do cabecalho
            br.readLine();

            while ((linha = br.readLine()) != null) {

            	// separando todos os dados por ;
                String[] dados = linha.split(";");

                // validando estrutura do CSV
                if (dados.length != 6) {
                    throw new CsvFormatoInvalidoException(
                            "Formato inválido no CSV de livros: " + linha);
                }

                try {
                    String isbn = dados[0];
                    String titulo = dados[1];
                    String autor = dados[2];
                    String categoria = dados[3];
                    int ano = Integer.parseInt(dados[4]);
                    boolean disponibilidade = Boolean.parseBoolean(dados[5]);

                    Livro livro = new Livro(
                            isbn,
                            titulo,
                            autor,
                            categoria,
                            ano,
                            disponibilidade
                    );

                    livros.add(livro);

                } catch (NumberFormatException e) {
                    throw new CsvFormatoInvalidoException(
                            "Erro de conversão numérica na linha: " + linha);
                }
            }

        } catch (CsvFormatoInvalidoException e) {
            // repassando excecao para as excecoes do pacote exception
            throw e;

        } catch (Exception e) {
            // lancando excecao para erro de processamento do csv
            throw new Exception(
                    "Erro ao processar arquivo CSV: " + e.getMessage());
        }

        return livros;
    }
}