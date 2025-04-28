package com.javaweb.app03.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.app03.model.Usuario;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();

    public boolean autenticar(String email, String senha) {
        return usuarios.stream()
                .anyMatch(u -> u.getEmail().equals(email) && u.getSenha().equals(senha));
    }

    public void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
