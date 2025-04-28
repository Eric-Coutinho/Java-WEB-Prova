package com.javaweb.app03.service;

import com.javaweb.app03.model.Usuario; 
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private static List<Usuario> usuarios = new ArrayList<>(Arrays.asList(
        new Usuario("Admin", "admin", "admin@example.com", "admin123", "admin123") //
    ));

    public Optional<Usuario> validarUsuario(String email, String senha) {
        System.out.println("Service: Validating user with email: " + email);
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email) &&
                usuario.getSenha().equals(senha)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty(); 
    }

    
    public boolean cadastrarUsuario(Usuario novoUsuario) {
    
        if (!novoUsuario.getSenha().equals(novoUsuario.getConfirmaSenha())) {
            return false; 
        }
        
        usuarios.add(novoUsuario);
        System.out.println("Service: User registered: " + novoUsuario.getEmail());
        return true; 
    }

    
    public List<Usuario> getTodosUsuarios() {
        return new ArrayList<>(usuarios); 
    }
}