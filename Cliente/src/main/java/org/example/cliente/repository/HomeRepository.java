package org.example.cliente.repository;

import java.time.LocalDate;
import java.util.List;

import org.example.cliente.entities.Transacao;


public class HomeRepository {

    public List<Transacao> findAll() {
        List<Transacao> transacoes = new java.util.ArrayList<>();
        transacoes.add(new Transacao(LocalDate.now(), "Salário", "Receita", "entrada", 3500.00D));
        transacoes.add( new Transacao(LocalDate.now(), "Supermercado", "Alimentação", "saída", 280.50D));
        return transacoes;
    }
    
}
