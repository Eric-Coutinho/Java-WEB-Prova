package com.javaweb.app03.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.javaweb.app03.model.Tarefas;

@Service
public class TarefasService {

    private final Map<String, List<Tarefas>> tarefasPorUsuario = new ConcurrentHashMap<>();

    public List<Tarefas> getTarefasPorUsuario(String emailUsuario) {
        return tarefasPorUsuario.getOrDefault(emailUsuario, new ArrayList<>());
    }

    public void adicionarTarefa(String emailUsuario, String descricao) {
        Tarefas novaTarefa = new Tarefas(descricao, emailUsuario);
        tarefasPorUsuario.computeIfAbsent(emailUsuario, k -> new ArrayList<>()).add(novaTarefa);
        System.out.println("Service: Task added for " + emailUsuario + ": " + descricao);
    }

    public boolean marcarComoConcluida(String emailUsuario, long tarefaId, String dataConclusaoStr) {
        List<Tarefas> tarefas = tarefasPorUsuario.get(emailUsuario);
        if (tarefas != null) {
            for (Tarefas tarefa : tarefas) {
                if (tarefa.getId() == tarefaId) {
                    tarefa.setConcluida(true);
                    if (dataConclusaoStr != null && !dataConclusaoStr.trim().isEmpty()) {
                        try {
                           LocalDate dataConclusao = LocalDate.parse(dataConclusaoStr, DateTimeFormatter.ISO_DATE);
                           tarefa.setDataConclusao(dataConclusao.format(DateTimeFormatter.ISO_DATE));
                        } catch (Exception e) {
                            System.err.println("Formato de data inválido: " + dataConclusaoStr + ". Usando data atual.");
                            tarefa.setDataConclusao(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
                        }
                    } else {
                         tarefa.setDataConclusao(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
                    }
                    System.out.println("Service: Task " + tarefaId + " marked as completed for " + emailUsuario);
                    return true;
                }
            }
        }
        System.out.println("Service: Task " + tarefaId + " not found for user " + emailUsuario);
        return false; 
    }

     public boolean removerTarefa(String emailUsuario, long tarefaId) {
        List<Tarefas> tarefas = tarefasPorUsuario.get(emailUsuario);
        if (tarefas != null) {
            boolean removed = tarefas.removeIf(tarefa -> tarefa.getId() == tarefaId);
            if (removed) {
                 System.out.println("Service: Task " + tarefaId + " removed for " + emailUsuario);
            }
            return removed;
        }
         System.out.println("Service: Task " + tarefaId + " not found for removal for user " + emailUsuario);
        return false;
     }
}