package com.javaweb.app03.Controller;

import com.javaweb.app03.model.Tarefas;
import com.javaweb.app03.model.Usuario; 
import com.javaweb.app03.service.TarefasService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/tarefas") 
public class TarefasController {

    private final TarefasService tarefasService;

    @Autowired
    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    private Usuario getUsuarioLogado(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogado");
    }

    @GetMapping
    public String mostrarTelaTarefas(HttpSession session, Model model) {
        Usuario usuario = getUsuarioLogado(session);
        if (usuario == null) {
            return "redirect:/login"; 
        }

        List<Tarefas> listaTarefas = tarefasService.getTarefasPorUsuario(usuario.getEmail());
        model.addAttribute("tarefas", listaTarefas);
        model.addAttribute("novaTarefa", new Tarefas()); 
        model.addAttribute("NomeUsuario", usuario.getNome()); 

        return "tarefas"; 
    }

    @PostMapping("/add")
    public String adicionarTarefa(@RequestParam String descricao, HttpSession session, Model model) {
         Usuario usuario = getUsuarioLogado(session);
        if (usuario == null) {
            return "redirect:/login"; 
        }

        if (descricao == null || descricao.trim().isEmpty()) {
             model.addAttribute("erroAdd", "A descrição da tarefa não pode ser vazia.");
             List<Tarefas> listaTarefas = tarefasService.getTarefasPorUsuario(usuario.getEmail());
             model.addAttribute("tarefas", listaTarefas);
             model.addAttribute("novaTarefa", new Tarefas());
             model.addAttribute("NomeUsuario", usuario.getNome());
             return "tarefas";
        }


        tarefasService.adicionarTarefa(usuario.getEmail(), descricao);
        return "redirect:/tarefas";
    }

    @PostMapping("/complete/{id}")
    public String marcarComoConcluida(@PathVariable long id,
                                     @RequestParam(required = false) String dataConclusao, // Torna opcional
                                     HttpSession session) {
         Usuario usuario = getUsuarioLogado(session);
        if (usuario == null) {
            return "redirect:/login";
        }

        tarefasService.marcarComoConcluida(usuario.getEmail(), id, dataConclusao);
        return "redirect:/tarefas";
    }

    
    @PostMapping("/delete/{id}")
    public String removerTarefa(@PathVariable long id, HttpSession session) {
        Usuario usuario = getUsuarioLogado(session);
        if (usuario == null) {
            return "redirect:/login";
        }
        tarefasService.removerTarefa(usuario.getEmail(), id);
        return "redirect:/tarefas";
    }
}