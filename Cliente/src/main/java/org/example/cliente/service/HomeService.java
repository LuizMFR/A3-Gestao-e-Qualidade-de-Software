package org.example.cliente.service;


import java.util.List;

import org.example.cliente.entities.Transacao;
import org.example.cliente.repository.HomeRepository;



public class HomeService {

    private HomeRepository homeRepository = new HomeRepository();
 
    public HomeService() {

    }

    public List<Transacao> getAllTransacoes() {
        return homeRepository.findAll();
    }   
}
