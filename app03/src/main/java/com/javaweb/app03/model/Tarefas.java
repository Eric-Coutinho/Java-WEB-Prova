package com.javaweb.app03.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefas {
    private static long nextId = 1;
    private long id;
    private String descricao;
    private boolean concluida;
    private String dataCriacao; 
    private String dataConclusao; 
    private String emailUsuario; 

    public Tarefas() {
        this.id = nextId++;
        this.concluida = false;
        this.dataCriacao = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

     public Tarefas(String descricao, String emailUsuario) {
        this();
        this.descricao = descricao;
        this.emailUsuario = emailUsuario;
    }

    

    public long getId() { return id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; } // Permitir set, se necessário

    public String getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(String dataConclusao) { this.dataConclusao = dataConclusao; }

    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }
}
