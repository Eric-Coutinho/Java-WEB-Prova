package com.javaweb.app03.model;

public class Tarefa {

    private String descricao;
    private boolean concluida;
    private String dataCriacao;
    private String dataConclusao;

    public Tarefa() {
    }

    public Tarefa(String descricao, String dataCriacao) {
        this.descricao = descricao;
        this.concluida = false;
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

}
