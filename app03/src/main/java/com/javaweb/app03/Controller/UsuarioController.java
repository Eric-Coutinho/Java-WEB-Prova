package com.javaweb.app03.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.javaweb.app03.model.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
    private static List<Usuario> usuarios = new ArrayList<>(Arrays.asList(
        new Usuario("Admin", "admin", "admin@example.com", "admin123", "admin123")
    ));

    @GetMapping("/login")
    public String mostrar_TelaLogin() {
        return "login";
    }
    
    @PostMapping("/validar")
    public String validarCredenciais(@ModelAttribute Usuario usr, Model modelo, HttpSession sessao) {
        System.out.println("Attempting login with email: " + usr.getEmail());
        System.out.println("Attempting login with password: " + usr.getSenha());
        
        for (Usuario usuario : usuarios) {
            System.out.println("Checking against user: " + usuario.getEmail());
            if (usuario.getEmail().equalsIgnoreCase(usr.getEmail()) && 
                usuario.getSenha().equalsIgnoreCase(usr.getSenha())) {
                sessao.setAttribute("usuarioLogado", usuario);
                return "redirect:/home";
            }
        }
        
        System.out.println("Login failed for email: " + usr.getEmail());
        modelo.addAttribute("msg", "Email ou Senha incorretos");
        return "login";
    }
        
    @GetMapping("/home")
    public String mostraTelaInicial(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null) {
            model.addAttribute("msg", "Sessao expirou ou usuario deslogado");
            return "redirect:/login";
        }
        else {
            model.addAttribute("NomeUsuario", usuario.getNome());
            return "home";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession sessao) {
        sessao.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }
    
    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        if (!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
            model.addAttribute("erro", "As senhas não coincidem");
            return "cadastro";
        }
        
        usuarios.add(usuario);
        return "redirect:/login";
    }
}
