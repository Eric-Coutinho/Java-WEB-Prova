package com.javaweb.app03.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaweb.app03.model.Tarefa;

@Service
public class TarefaService {
    private final Map<String, List<Tarefa>> tarefasPorUsuario = new HashMap<>();

    public List<Tarefa> listar(String login) {
        return tarefasPorUsuario.computeIfAbsent(login, k -> new ArrayList<>());
    }

    public void adicionar(String login, Tarefa tarefa) {
        listar(login).add(tarefa);
    }

    public void concluir(String login, int indice, String dataConclusao) {
        List<Tarefa> lista = listar(login);
        if (indice >= 0 && indice < lista.size()) {
            Tarefa t = lista.get(indice);
            t.setConcluida(true);
            t.setDataConclusao(dataConclusao);
        }
    }
}
