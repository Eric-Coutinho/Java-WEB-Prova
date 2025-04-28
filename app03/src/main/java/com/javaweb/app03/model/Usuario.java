package com.javaweb.app03.model;

public class Usuario {
    private String nome;
    private String perfil;
    private String email;
    private String senha;
    private String confirmaSenha;

    public Usuario() { }

    public Usuario(String nome, String perfil, String email, String senha, String confirmaSenha) {
        this.nome = nome;
        this.perfil = perfil;
        this.email = email;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
}