package com.javaweb.app03.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaweb.app03.model.Usuario;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();

    public Optional<Usuario> autenticar(String email, String senha) {
        return usuarios.stream()
            .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
            .findFirst();
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream()
            .filter(u -> u.getEmail().equals(email))
            .findFirst();
    }
}
