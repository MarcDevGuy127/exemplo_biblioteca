package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private int id;
    private String isbn;
    private String matricula;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    private String status;

    // definindo cada objeto emprestimo
    public Emprestimo(
            int id,
            String isbn,
            String matricula,
            LocalDate dataEmprestimo,
            LocalDate dataPrevistaDevolucao,
            LocalDate dataDevolucao,
            String status) {

        this.id = id;
        this.isbn = isbn;
        this.matricula = matricula;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    // id pode ser consultado
    public int getId() {
        return id;
    }

    // isbn pode ser consultado
    public String getIsbn() {
        return isbn;
    }

    // matricula pode ser consultada
    public String getMatricula() {
        return matricula;
    }

    // data de emprestimo pode ser consultada
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    // data prevista de devolucao pode ser consultada
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    // data de devolucao pode ser consultada
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    // status pode ser consultado
    public String getStatus() {
        return status;
    }

    // apenas a data de devolucao pode ser modificada
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // apenas a situacao dos status pode ser modificado
    public void setStatus(String status) {
        this.status = status;
    }

    // metodos para atender regras de negocio
    
    // metodo para caso o status do livro for como devolvido
    public boolean estaDevolvido() {
        return "DEVOLVIDO".equalsIgnoreCase(status);
    }

    // metodo para caso o status do livro for como emprestado
    public boolean estaEmprestado() {
        return "EMPRESTADO".equalsIgnoreCase(status);
    }

    // metodo para caso o status do livro for como atrasado
    public boolean estaAtrasado() {

        if (dataDevolucao == null) {
            return LocalDate.now()
                    .isAfter(dataPrevistaDevolucao);
        }

        return dataDevolucao
                .isAfter(dataPrevistaDevolucao);
    }

    // metodo para consultar dias de atraso caso livro estiver em situacao de atraso
    public long getDiasAtraso() {

        if (!estaAtrasado()) {
            return 0;
        }

        // condicional ternario para configurar data prevista para devolucao
        LocalDate dataFinal =
                (dataDevolucao == null)
                        ? LocalDate.now()
                        : dataDevolucao;

        return ChronoUnit.DAYS.between(
                dataPrevistaDevolucao,
                dataFinal);
    }

    // convertendo dados para string

    @Override
    public String toString() {

        return "Emprestimo [id=" + id +
                ", isbn=" + isbn +
                ", matricula=" + matricula +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevistaDevolucao=" + dataPrevistaDevolucao +
                ", dataDevolucao=" + dataDevolucao +
                ", status=" + status +
                "]";
    }
}